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

package org.opengoofy.congomall.biz.customer.user.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding({Source.class, Sink.class})
@EnableFeignClients("org.opengoofy.congomall.biz.customer.user.infrastructure.remote")
@SpringBootApplication(scanBasePackages = "org.opengoofy.congomall.biz.customer.user")
@MapperScan("org.opengoofy.congomall.biz.customer.user.infrastructure.dao")
public class CustomerUserMockApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CustomerUserMockApplication.class, args);
    }
}
