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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存穿透布隆过滤器
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@Data
@ConfigurationProperties(prefix = BloomFilterPenetrateProperties.PREFIX)
public class BloomFilterPenetrateProperties {
    
    public static final String PREFIX = "spring.redis.bloom-filter";
    
    /**
     * 布隆过滤器实例名称
     */
    private String name = "cache_penetration_bloom_filter";
    
    /**
     * 每个元素的预期插入量
     */
    private Long expectedInsertions = 64000L;
    
    /**
     * 预期错误概率
     */
    private Double falseProbability = 0.03D;
}
