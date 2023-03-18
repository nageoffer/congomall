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

package org.opengoofy.congomall.biz.customer.user;

import org.opengoofy.congomall.biz.customer.user.infrastructure.dao.entity.CustomerUserDO;
import org.opengoofy.congomall.biz.customer.user.infrastructure.dao.mapper.CustomerUserRepositoryMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

/**
 * C 端用户测试用例
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@SpringBootApplication
public class CustomerUserTest {
    
    private CustomerUserRepositoryMapper customerUserRepositoryMapper;
    
    @Before
    public void before() {
        ConfigurableApplicationContext context = SpringApplication.run(CustomerUserTest.class);
        customerUserRepositoryMapper = context.getBean(CustomerUserRepositoryMapper.class);
    }
    
    @Test
    public void assertUpdateCustomerUserTest() {
        CustomerUserDO saveCustomerUserDO = new CustomerUserDO();
        saveCustomerUserDO.setName("小马哥");
        saveCustomerUserDO.setAccountNumber("xiao-ma-ge");
        saveCustomerUserDO.setPassword("xiao-ma-ge");
        saveCustomerUserDO.setPhone("15601166692");
        saveCustomerUserDO.setMail("m7798432@163.com");
        saveCustomerUserDO.setAge(18);
        saveCustomerUserDO.setGender(0);
        saveCustomerUserDO.setAvatar("https://oss.aliyun.com/images");
        saveCustomerUserDO.setCreateTime(new Date());
        saveCustomerUserDO.setUpdateTime(new Date());
        saveCustomerUserDO.setDelFlag(0);
        customerUserRepositoryMapper.insert(saveCustomerUserDO);
        LambdaUpdateWrapper<CustomerUserDO> updateWrapper = Wrappers.lambdaUpdate(CustomerUserDO.class)
                .set(CustomerUserDO::getAvatar, null)
                .eq(CustomerUserDO::getId, saveCustomerUserDO.getId());
        customerUserRepositoryMapper.update(null, updateWrapper);
    }
}
