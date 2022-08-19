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

import cn.mall4j.biz.customer.user.application.req.UserLoginCommand;
import cn.mall4j.biz.customer.user.application.req.UserRegisterCommand;
import cn.mall4j.biz.customer.user.application.req.UserVerifyCodeCommand;
import cn.mall4j.biz.customer.user.application.resp.UserLoginRespDTO;
import cn.mall4j.biz.customer.user.application.resp.UserRegisterRespDTO;
import cn.mall4j.biz.customer.user.application.service.CustomerUserService;
import cn.mall4j.springboot.starter.convention.result.Result;
import cn.mall4j.springboot.starter.log.annotation.MLog;
import cn.mall4j.springboot.starter.web.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * C 端用户控制器
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "C 端用户")
@RequestMapping("/customer-user")
public class CustomerUserController {
    
    private final CustomerUserService customerUserService;
    
    @ApiOperation(value = "验证发送", notes = "包含注册验证码、登录验证等")
    @PostMapping("/verify/code/send")
    public Result<Void> verifyCodeSend(@RequestBody @Valid UserVerifyCodeCommand requestParam) {
        customerUserService.verifyCodeSend(requestParam);
        return Results.success();
    }
    
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<UserRegisterRespDTO> register(@RequestBody @Valid UserRegisterCommand requestParam) {
        UserRegisterRespDTO result = customerUserService.register(requestParam);
        return Results.success(result);
    }
    
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(@RequestBody @Valid UserLoginCommand requestParam) {
        UserLoginRespDTO result = customerUserService.login(requestParam);
        return Results.success(result);
    }
}
