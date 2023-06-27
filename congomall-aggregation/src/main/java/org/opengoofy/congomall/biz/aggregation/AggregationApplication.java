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

package org.opengoofy.congomall.biz.aggregation;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.opengoofy.congomall.biz.customer.user.infrastructure.mq.messaging.UserSink;
import org.opengoofy.congomall.biz.customer.user.infrastructure.mq.messaging.UserSource;
import org.opengoofy.congomall.biz.order.infrastructure.mq.messaging.OrderSink;
import org.opengoofy.congomall.biz.order.infrastructure.mq.messaging.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableDynamicThreadPool
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "org.opengoofy.congomall.bff")
@MapperScan(value = {
        "org.opengoofy.congomall.biz.*.infrastructure.dao.mapper",
        "org.opengoofy.congomall.biz.customer.user.infrastructure.dao.mapper",
        "org.opengoofy.congomall.biz.bff.dao.mapper"
})
@EnableBinding(value = {
        UserSource.class, UserSink.class, OrderSource.class, OrderSink.class
})
@EnableFeignClients(value = {
        "org.opengoofy.congomall.biz.order.infrastructure.remote",
        "org.opengoofy.congomall.biz.bff.remote",
        "org.opengoofy.congomall.biz.customer.user.infrastructure.remote"
})
@SpringBootApplication(scanBasePackages = {
        "org.opengoofy.congomall",
})
public class AggregationApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AggregationApplication.class, args);
    }
}
