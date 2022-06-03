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

package cn.mall4j.biz.customer.user.application.service.handler;

import cn.mall4j.biz.customer.user.application.assembler.CustomerUserToDTOAssembler;
import cn.mall4j.biz.customer.user.application.req.UserRegisterCommand;
import cn.mall4j.biz.customer.user.application.resp.UserRegisterRespDTO;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserAccountNumber;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserName;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserPassword;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserPhone;
import cn.mall4j.biz.customer.user.domain.entity.CustomerUser;
import cn.mall4j.biz.customer.user.domain.repository.CustomerUserRepository;
import cn.mall4j.ddd.framework.core.domain.CommandHandler;
import cn.mall4j.springboot.starter.cache.DistributedCache;
import cn.mall4j.springboot.starter.cache.toolkit.CacheUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static cn.mall4j.biz.customer.user.common.CacheConstant.REGISTER_USER_VERIFY_CODE;

/**
 * C 端用户注册处理器
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Component
@AllArgsConstructor
public class CustomerUserRegisterCommandHandler implements CommandHandler<UserRegisterCommand, UserRegisterRespDTO> {
    
    private final CustomerUserRepository customerUserRepository;
    
    private final CustomerUserToDTOAssembler customerUserAssembler;
    
    private final DistributedCache distributedCache;
    
    @Override
    public UserRegisterRespDTO handler(UserRegisterCommand requestParam) {
        CustomerUser customerUser = CustomerUser.builder()
                .userName(new CustomerUserName(requestParam.getUsername()))
                .phone(new CustomerUserPhone(requestParam.getPhone()))
                .accountNumber(new CustomerUserAccountNumber(requestParam.getAccountNumber()))
                .password(new CustomerUserPassword(requestParam.getPassword()))
                .receiver(requestParam.getMail())
                .verifyCode(requestParam.getMailValidCode())
                .build();
        // 获取缓存中的验证码
        String verifyCode = distributedCache.get(CacheUtil.buildKey(REGISTER_USER_VERIFY_CODE, customerUser.getReceiver()), String.class);
        // 检查验证码正确性
        customerUser.checkoutValidCode(verifyCode);
        CustomerUser result = customerUserRepository.register(customerUser);
        return customerUserAssembler.customerUserToUserRegisterRespDTO(result);
    }
}
