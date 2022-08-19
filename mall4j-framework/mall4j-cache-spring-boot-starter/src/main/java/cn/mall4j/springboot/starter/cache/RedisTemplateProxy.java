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

package cn.mall4j.springboot.starter.cache;

import cn.mall4j.springboot.starter.base.Singleton;
import cn.mall4j.springboot.starter.cache.config.RedisDistributedProperties;
import cn.mall4j.springboot.starter.cache.core.CacheGetFilter;
import cn.mall4j.springboot.starter.cache.core.CacheGetIfAbsent;
import cn.mall4j.springboot.starter.cache.core.CacheLoader;
import cn.mall4j.springboot.starter.cache.toolkit.CacheUtil;
import cn.mall4j.springboot.starter.cache.toolkit.FastJson2Util;
import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

/**
 * Redis 缓存代理
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@RequiredArgsConstructor
public class RedisTemplateProxy implements DistributedCache {
    
    private final StringRedisTemplate stringRedisTemplate;
    
    private final RedisDistributedProperties redisProperties;
    
    private final RedissonClient redissonClient;
    
    private final RBloomFilter<String> cachePenetrationBloomFilter;
    
    @Override
    public <T> T get(String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (String.class.isAssignableFrom(clazz)) {
            return (T) value;
        }
        return JSON.parseObject(value, FastJson2Util.buildType(clazz));
    }
    
    @Override
    public void put(String key, Object value) {
        put(key, value, redisProperties.getValueTimout());
    }
    
    @Override
    public Boolean putIfAllAbsent(@NotNull Collection<String> keys) {
        String scriptPathKey = "lua/putIfAllAbsent.lua";
        DefaultRedisScript actual = Singleton.get(scriptPathKey, () -> {
            DefaultRedisScript redisScript = new DefaultRedisScript();
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(scriptPathKey)));
            redisScript.setResultType(Boolean.class);
            return redisScript;
        });
        Object result = stringRedisTemplate.execute(actual, Lists.newArrayList(keys), redisProperties.getValueTimout().toString());
        return result == null ? false : (Boolean) result;
    }
    
    @Override
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }
    
    @Override
    public Long delete(Collection<String> keys) {
        return stringRedisTemplate.delete(keys);
    }
    
    @Override
    public <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout) {
        T result = get(key, clazz);
        if (!CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        return loadAndSet(key, cacheLoader, timeout, false);
    }
    
    @Override
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout) {
        return safeGet(key, clazz, cacheLoader, timeout, null, null);
    }
    
    @Override
    public <T> T safeGet(String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, CacheGetFilter<String> cacheGetFilter, CacheGetIfAbsent<String> cacheGetIfAbsent) {
        T result = get(key, clazz);
        // 缓存不等于空返回；缓存为空判断布隆过滤器是否存在，不存在返回空；如果前两者都不成立，通过函数判断是否返回空
        if (!CacheUtil.isNullOrBlank(result) || !cachePenetrationBloomFilter.contains(key) || Optional.ofNullable(cacheGetFilter).map(each -> each.filter(key)).orElse(false)) {
            return result;
        }
        RLock lock = redissonClient.getLock(CacheUtil.buildKey("distributed_lock_lock_get", key));
        lock.lock();
        try {
            // 双重判定锁，减轻数据库访问压力
            if (CacheUtil.isNullOrBlank(result = get(key, clazz))) {
                // 如果访问 load 数据为空，通过函数执行后置操作
                if (CacheUtil.isNullOrBlank(result = loadAndSet(key, cacheLoader, timeout, true))) {
                    Optional.ofNullable(cacheGetIfAbsent).ifPresent(each -> each.execute(key));
                }
            }
        } finally {
            lock.unlock();
        }
        return result;
    }
    
    @Override
    public void put(String key, Object value, long timeout) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual, timeout, redisProperties.getValueTimeUnit());
    }
    
    @Override
    public void safePut(String key, Object value, long timeout) {
        put(key, value, timeout);
        // key 放入步隆过滤器
        cachePenetrationBloomFilter.add(key);
    }
    
    @Override
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
    
    @Override
    public Long countExistingKeys(String... keys) {
        return stringRedisTemplate.countExistingKeys(Lists.newArrayList(keys));
    }
    
    private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader, long timeout, boolean safe) {
        T result = cacheLoader.load();
        if (CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        if (safe) {
            safePut(key, result, timeout);
        } else {
            put(key, result, timeout);
        }
        return result;
    }
}
