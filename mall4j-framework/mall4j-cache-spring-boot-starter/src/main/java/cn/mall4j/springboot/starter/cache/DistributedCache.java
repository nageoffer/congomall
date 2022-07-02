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

import cn.mall4j.springboot.starter.cache.core.CacheGetFilter;
import cn.mall4j.springboot.starter.cache.core.CacheGetIfAbsent;
import cn.mall4j.springboot.starter.cache.core.CacheLoader;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 分布式缓存
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public interface DistributedCache extends Cache {
    
    /**
     * 获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     *
     * @param key
     * @param clazz
     * @param cacheLoader
     * @param timeout
     * @param <T>
     * @return
     */
    <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout);
    
    /**
     * 以一种"安全"的方式获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     *
     * @param key
     * @param clazz
     * @param cacheLoader
     * @param timeout
     * @param <T>
     * @return
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout);
    
    /**
     * 以一种"安全"的方式获取缓存，如查询结果为空，调用 cacheLoader 加载缓存
     *
     * @param key
     * @param clazz
     * @param cacheLoader
     * @param timeout
     * @param cacheCheckFilter
     * @param cacheGetIfAbsent
     * @param <T>
     * @return
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, CacheGetFilter<String> cacheCheckFilter, CacheGetIfAbsent<String> cacheGetIfAbsent);
    
    /**
     * 放入缓存，自定义超时时间
     *
     * @param key
     * @param value
     * @param timeout
     */
    void put(@NotBlank String key, Object value, long timeout);
    
    /**
     * 放入缓存，自定义超时时间
     * 并将 key 加入步隆过滤器，配置 {@link DistributedCache#safeGet(String, Class, CacheLoader, long)}
     *
     * @param key
     * @param value
     * @param timeout
     */
    void safePut(@NotBlank String key, Object value, long timeout);
    
    /**
     * 统计指定 key 的存在数量
     *
     * @param keys
     * @return
     */
    Long countExistingKeys(@NotNull String... keys);
}
