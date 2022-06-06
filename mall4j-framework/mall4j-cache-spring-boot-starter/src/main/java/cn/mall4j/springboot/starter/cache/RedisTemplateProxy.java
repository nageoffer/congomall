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

import cn.mall4j.springboot.starter.cache.config.RedisDistributedProperties;
import cn.mall4j.springboot.starter.cache.core.CacheLoader;
import cn.mall4j.springboot.starter.cache.toolkit.CacheUtil;
import cn.mall4j.springboot.starter.cache.toolkit.FastJson2Util;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.validation.constraints.NotBlank;

/**
 * Redis 缓存代理
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
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
    public <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout) {
        T result = get(key, clazz);
        if (!CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        return loadAndSet(key, cacheLoader, timeout);
    }
    
    @Override
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout) {
        T result = get(key, clazz);
        if (!CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        // 判断布隆过滤器是否存在，存在返回空
        if (cachePenetrationBloomFilter.contains(key)) {
            return null;
        }
        RLock lock = redissonClient.getLock(CacheUtil.buildKey("distributed_lock_lock_get", key));
        lock.lock();
        try {
            // 双重判定锁，减轻数据库访问压力
            if (CacheUtil.isNullOrBlank(result = get(key, clazz))) {
                // 通过 load 接口加载为空，触发缓存穿透条件，把 key 放入布隆过滤器
                if (CacheUtil.isNullOrBlank(result = loadAndSet(key, cacheLoader, timeout))) {
                    cachePenetrationBloomFilter.add(key);
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
    
    private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader, long timeout) {
        T result = cacheLoader.load();
        if (CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        put(key, result, timeout);
        return result;
    }
}
