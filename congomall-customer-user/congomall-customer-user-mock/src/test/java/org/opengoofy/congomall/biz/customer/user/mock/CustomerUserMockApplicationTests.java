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

import cn.hutool.crypto.digest.MD5;
import org.opengoofy.congomall.biz.customer.user.domain.dp.*;
import org.opengoofy.congomall.biz.customer.user.domain.aggregate.CustomerUser;
import org.opengoofy.congomall.biz.customer.user.domain.repository.CustomerUserRepository;
import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.opengoofy.congomall.springboot.starter.distributedid.SnowflakeIdUtil;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class CustomerUserMockApplicationTests {
    
    private final Faker faker = new Faker(Locale.CHINA);
    
    @Test
    public void mockCustomerUserInfo() {
        CustomerUserRepository customerUserRepository = ApplicationContextHolder.getBean(CustomerUserRepository.class);
        CustomerUser customerUser = CustomerUser.builder()
                .customerUserId(SnowflakeIdUtil.nextId())
                .accountNumber(new CustomerUserAccountNumber(faker.number().digits(10)))
                .phone(new CustomerUserPhone(faker.phoneNumber().cellPhone()))
                .password(new CustomerUserPassword(MD5.create().digestHex(faker.number().digits(10))))
                .username(new CustomerUserName(faker.funnyName().name()))
                .mail(new CustomerUserMail(faker.number().digits(10) + "@163.com"))
                .build();
        customerUserRepository.register(customerUser);
    }
}
