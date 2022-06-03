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

package cn.mall4j.springboot.starter.cache.config;

import cn.mall4j.springboot.starter.cache.RedisKeySerializer;
import cn.mall4j.springboot.starter.cache.RedisTemplateProxy;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 缓存配置自动装配
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@AllArgsConstructor
@EnableConfigurationProperties(RedisDistributedProperties.class)
public class CacheAutoConfiguration {
    
    private final RedisDistributedProperties redisDistributedProperties;
    
    @Bean
    public RedisKeySerializer redisKeySerializer() {
        String prefix = redisDistributedProperties.getPrefix();
        String prefixCharset = redisDistributedProperties.getPrefixCharset();
        return new RedisKeySerializer(prefix, prefixCharset);
    }
    
    @Bean
    public RedisTemplateProxy redisTemplateProxy(RedisKeySerializer redisKeySerializer, StringRedisTemplate stringRedisTemplate) {
        stringRedisTemplate.setKeySerializer(redisKeySerializer);
        return new RedisTemplateProxy(stringRedisTemplate, redisDistributedProperties);
    }
}
