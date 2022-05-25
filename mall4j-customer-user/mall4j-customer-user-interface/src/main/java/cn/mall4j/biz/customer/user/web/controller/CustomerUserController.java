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

package cn.mall4j.biz.customer.user.web.controller;

import cn.mall4j.biz.customer.user.api.client.CustomerUserClient;
import cn.mall4j.biz.customer.user.api.req.UserRegisterCommand;
import cn.mall4j.springboot.starter.convention.Result;
import cn.mall4j.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.RestController;

/**
 * C端用户控制器
 */
@RestController
public class CustomerUserController implements CustomerUserClient {
    
    public Result<Void> register(UserRegisterCommand requestParam) {
        // TODO
        return Results.success();
    }
}
