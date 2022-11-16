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

package org.opengoofy.congomall.biz.customer.user.application.service.impl;

import org.opengoofy.congomall.biz.customer.user.application.req.UserLoginCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.UserRegisterCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.UserVerifyCodeCommand;
import org.opengoofy.congomall.biz.customer.user.application.service.handler.login.MailLoginCommandHandler;
import org.opengoofy.congomall.biz.customer.user.application.service.handler.verify.MailLoginVerifyCommandHandler;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserLoginRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserRegisterRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.service.CustomerUserService;
import org.opengoofy.congomall.biz.customer.user.application.service.handler.verify.MailRegisterVerifyCommandHandler;
import org.opengoofy.congomall.ddd.framework.core.domain.CommandHandler;
import org.opengoofy.congomall.springboot.starter.design.pattern.strategy.AbstractStrategyChoose;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * C 端用户接口
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Service
@AllArgsConstructor
public class CustomerUserServiceImpl implements CustomerUserService {
    
    private final CommandHandler<UserRegisterCommand, UserRegisterRespDTO> customerUserRegisterCommandHandler;
    
    private final AbstractStrategyChoose abstractStrategyChoose;
    
    @Override
    public void verifyCodeSend(UserVerifyCodeCommand requestParam) {
        String mark = requestParam.getType() + "_" + requestParam.getPlatform();
        /**
         * site
         * {@link MailLoginVerifyCommandHandler}
         * {@link MailRegisterVerifyCommandHandler}
         * ...
         */
        abstractStrategyChoose.chooseAndExecute(mark, requestParam);
    }
    
    @Override
    public UserRegisterRespDTO register(UserRegisterCommand requestParam) {
        UserRegisterRespDTO result = customerUserRegisterCommandHandler.handler(requestParam);
        return result;
    }
    
    @Override
    public UserLoginRespDTO login(UserLoginCommand requestParam) {
        /**
         * site
         * {@link MailLoginCommandHandler}
         */
        return abstractStrategyChoose.chooseAndExecuteResp(requestParam.getLoginType(), requestParam);
    }
}
