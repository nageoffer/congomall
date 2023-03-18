/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.springboot.starter.idempotent.core.spel;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.opengoofy.congomall.springboot.starter.cache.DistributedCache;
import org.opengoofy.congomall.springboot.starter.idempotent.annotation.Idempotent;
import org.opengoofy.congomall.springboot.starter.idempotent.core.AbstractIdempotentTemplate;
import org.opengoofy.congomall.springboot.starter.idempotent.core.IdempotentAspect;
import org.opengoofy.congomall.springboot.starter.idempotent.core.IdempotentContext;
import org.opengoofy.congomall.springboot.starter.idempotent.core.IdempotentParamWrapper;
import org.opengoofy.congomall.springboot.starter.idempotent.core.RepeatConsumptionException;
import org.opengoofy.congomall.springboot.starter.idempotent.toolkit.LogUtil;
import org.opengoofy.congomall.springboot.starter.idempotent.toolkit.SpELUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * 基于 SpEL 方法验证请求幂等性，适用于 MQ 场景
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RequiredArgsConstructor
public final class IdempotentSpELByMQExecuteHandler extends AbstractIdempotentTemplate implements IdempotentSpELService {
    
    private final DistributedCache distributedCache;
    private final RedissonClient redissonClient;
    
    private final static String LOCK = "lock:spEL:MQ";
    private final static String WRAPPER = "wrapper:spEL:MQ";
    
    @SneakyThrows
    @Override
    protected IdempotentParamWrapper buildWrapper(ProceedingJoinPoint joinPoint) {
        Idempotent idempotent = IdempotentAspect.getIdempotent(joinPoint);
        String key = (String) SpELUtil.parseKey(idempotent.key(), ((MethodSignature) joinPoint.getSignature()).getMethod(), joinPoint.getArgs());
        return IdempotentParamWrapper.builder().lockKey(key).build();
    }
    
    @Override
    public void handler(IdempotentParamWrapper wrapper) {
        String lockKey = wrapper.getIdempotent().uniqueKeyPrefix() + "lock:" + wrapper.getLockKey();
        String uniqueKey = wrapper.getIdempotent().uniqueKeyPrefix() + wrapper.getLockKey();
        RLock lock = redissonClient.getLock(lockKey);
        if (!lock.tryLock() || distributedCache.get(uniqueKey, Object.class) != null) {
            LogUtil.getLog(wrapper.getJoinPoint()).warn("[{}] MQ重复消费", uniqueKey);
            throw new RepeatConsumptionException();
        }
        IdempotentContext.put(LOCK, lock);
        IdempotentContext.put(WRAPPER, wrapper);
    }
    
    @Override
    public void postProcessing() {
        IdempotentParamWrapper wrapper = (IdempotentParamWrapper) IdempotentContext.getKey(WRAPPER);
        if (wrapper != null) {
            Idempotent idempotent = wrapper.getIdempotent();
            String uniqueKey = idempotent.uniqueKeyPrefix() + wrapper.getLockKey();
            try {
                // 重点在于 Key 和过期时间，所以这里 Key 对应的 Val 任意设置字符串即可
                distributedCache.put(uniqueKey, "", idempotent.keyTimeout(), TimeUnit.SECONDS);
            } catch (Throwable ex) {
                LogUtil.getLog(wrapper.getJoinPoint()).error("[{}] 设置MQ防重令牌失败", uniqueKey);
            }
            RLock lock = null;
            try {
                lock = (RLock) IdempotentContext.getKey(LOCK);
            } finally {
                if (lock != null) {
                    lock.unlock();
                }
            }
        }
    }
}
