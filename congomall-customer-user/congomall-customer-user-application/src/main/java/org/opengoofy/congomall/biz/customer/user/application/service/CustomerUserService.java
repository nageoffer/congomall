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

package org.opengoofy.congomall.biz.customer.user.application.service;

import org.opengoofy.congomall.biz.customer.user.application.req.UserLoginCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.UserRegisterCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.UserVerifyCodeCommand;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserLoginRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserRegisterRespDTO;

/**
 * C 端用户接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface CustomerUserService {
    
    /**
     * C 端用户验证
     *
     * @param requestParam 验证用户入参
     */
    void verifyCodeSend(UserVerifyCodeCommand requestParam);
    
    /**
     * C 端用户注册
     *
     * @param requestParam 注册用户入参
     * @return 注册用户成功后信息
     */
    UserRegisterRespDTO register(UserRegisterCommand requestParam);
    
    /**
     * C 端用户登录
     *
     * @param requestParam 用户登录入参
     * @return 用户登录返回信息
     */
    UserLoginRespDTO login(UserLoginCommand requestParam);
    
    /**
     * 通过 Token 检查用户是否登录
     *
     * @param accessToken 用户登录 Token
     * @return 用户登录信息
     */
    UserLoginRespDTO checkLogin(String accessToken);
    
    /**
     * 用户退出登录
     *
     * @param accessToken 用户登录 Token
     */
    void logout(String accessToken);
}
