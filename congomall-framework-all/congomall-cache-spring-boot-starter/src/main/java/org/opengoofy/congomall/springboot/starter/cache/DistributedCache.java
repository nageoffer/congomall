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

package org.opengoofy.congomall.springboot.starter.cache;

import org.opengoofy.congomall.springboot.starter.cache.core.CacheGetFilter;
import org.opengoofy.congomall.springboot.starter.cache.core.CacheLoader;
import org.opengoofy.congomall.springboot.starter.cache.core.CacheGetIfAbsent;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * 分布式缓存
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public interface DistributedCache extends Cache {
    
    /**
     * 获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     */
    <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout);
    
    /**
     * 以一种"安全"的方式获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout);
    
    /**
     * 以一种"安全"的方式获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, CacheGetFilter<String> cacheCheckFilter, CacheGetIfAbsent<String> cacheGetIfAbsent);
    
    /**
     * 放入缓存，自定义超时时间
     */
    void put(@NotBlank String key, Object value, long timeout);
    
    /**
     * 放入缓存，自定义超时时间
     */
    void put(@NotBlank String key, Object value, long timeout, TimeUnit timeUnit);
    
    /**
     * 放入缓存，自定义超时时间
     * 并将 key 加入步隆过滤器，配置 {@link DistributedCache#safeGet(String, Class, CacheLoader, long)}
     */
    void safePut(@NotBlank String key, Object value, long timeout);
    
    /**
     * 统计指定 key 的存在数量
     */
    Long countExistingKeys(@NotNull String... keys);
}
