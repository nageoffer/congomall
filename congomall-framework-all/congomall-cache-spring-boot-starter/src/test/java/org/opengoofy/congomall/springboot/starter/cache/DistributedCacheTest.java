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

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * 分布式缓存测试
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@SpringBootApplication
public class DistributedCacheTest {
    
    private DistributedCache distributedCache;
    
    @Before
    public void before() {
        ConfigurableApplicationContext context = SpringApplication.run(DistributedCacheTest.class);
        distributedCache = context.getBean(DistributedCache.class);
    }
    
    @Test
    public void assertSafePut() {
        distributedCache.safePut("test", "test_value", 5000L, null);
        String actual = distributedCache.get("test", String.class);
        Assert.assertEquals(actual, "test_value");
    }
    
    @Test
    public void assertSecureGet() {
        distributedCache.safeGet("test", String.class, () -> "test_value", 5000L);
        String actual = distributedCache.get("test", String.class);
        Assert.assertEquals(actual, "test_value");
    }
    
    @Test
    public void assertBloomFilterSecureGet() {
        for (int i = 0; i < 2; i++) {
            distributedCache.safeGet("test", String.class, () -> "", 5000L);
        }
    }
    
    @Test
    public void assertPutIfAllAbsent() {
        List<String> keys = Lists.newArrayList("name", "age");
        Boolean result = distributedCache.putIfAllAbsent(keys);
        Assert.assertTrue(result);
        keys.forEach(each -> {
            String name = distributedCache.get("name", String.class);
            Assert.assertEquals(name, "default");
        });
        Boolean resultFalse = distributedCache.putIfAllAbsent(keys);
        Assert.assertFalse(resultFalse);
    }
}
