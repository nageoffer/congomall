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

package org.opengoofy.congomall.biz.customer.user.application.service.handler.login;

import org.opengoofy.congomall.biz.customer.user.application.req.UserLoginCommand;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserLoginRespDTO;
import org.opengoofy.congomall.biz.customer.user.domain.aggregate.CustomerUser;
import org.opengoofy.congomall.biz.customer.user.domain.repository.CustomerUserRepository;
import org.opengoofy.congomall.springboot.starter.cache.DistributedCache;
import org.opengoofy.congomall.springboot.starter.cache.toolkit.CacheUtil;
import org.opengoofy.congomall.springboot.starter.designpattern.strategy.AbstractExecuteStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static org.opengoofy.congomall.biz.customer.user.domain.common.CacheConstant.LOGIN_USER_VERIFY_CODE;

/**
 * 邮箱登录
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Component
@AllArgsConstructor
public class MailLoginCommandHandler implements AbstractExecuteStrategy<UserLoginCommand, UserLoginRespDTO> {
    
    private final DistributedCache distributedCache;
    
    private final CustomerUserRepository customerUserRepository;
    
    @Override
    public String mark() {
        return "customer_user_login_mail";
    }
    
    @Override
    public UserLoginRespDTO executeResp(UserLoginCommand requestParam) {
        CustomerUser customerUser = CustomerUser.builder().verifyCode(requestParam.getMailValidCode()).build();
        // 获取缓存中的验证码
        String verifyCode = distributedCache.get(CacheUtil.buildKey(LOGIN_USER_VERIFY_CODE, requestParam.getMail()), String.class);
        // 检查验证码正确性
        customerUser.checkoutValidCode(verifyCode);
        CustomerUser actual = customerUserRepository.findByMail(requestParam.getMail());
        String accessToken = actual.generateAccessToken();
        return new UserLoginRespDTO(actual.getCustomerUserId(), actual.getUsername(), actual.getAccountNumber(), accessToken);
    }
}
