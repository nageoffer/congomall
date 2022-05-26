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

package cn.mall4j.biz.customer.user.application.service.impl;

import cn.mall4j.biz.customer.user.application.req.UserRegisterCommand;
import cn.mall4j.biz.customer.user.application.resp.UserRegisterRespDTO;
import cn.mall4j.biz.customer.user.application.service.CustomerUserService;
import cn.mall4j.ddd.framework.core.domain.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * C 端用户接口
 */
@Service
@AllArgsConstructor
public class CustomerUserServiceImpl implements CustomerUserService {
    
    private final CommandHandler<UserRegisterCommand, UserRegisterRespDTO> customerUserRegisterCommandHandler;
    
    @Override
    public UserRegisterRespDTO register(UserRegisterCommand requestParam) {
        UserRegisterRespDTO result = customerUserRegisterCommandHandler.handler(requestParam);
        return result;
    }
}
