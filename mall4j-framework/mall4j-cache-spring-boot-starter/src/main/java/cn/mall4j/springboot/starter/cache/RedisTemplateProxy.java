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
import cn.mall4j.springboot.starter.cache.toolkit.FastJson2Util;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis 缓存代理
 *
 * @author chen.ma
 * @github https://github.com/longtai-cn
 */
@RequiredArgsConstructor
public class RedisTemplateProxy implements DistributedCache {
    
    private final StringRedisTemplate stringRedisTemplate;
    
    private final RedisDistributedProperties redisProperties;
    
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
    public void put(String key, Object value, long timeout) {
        String actual = value instanceof String ? (String) value : JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, actual, timeout, redisProperties.getValueTimeUnit());
    }
}
