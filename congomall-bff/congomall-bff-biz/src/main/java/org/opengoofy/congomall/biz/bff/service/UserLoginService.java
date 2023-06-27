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

package org.opengoofy.congomall.biz.bff.service;

import org.opengoofy.congomall.biz.bff.dto.req.adapter.UserLoginAdapterRepDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.GeeTestAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.UserLoginAdapterRespDTO;

/**
 * 用户登录接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface UserLoginService {
    
    /**
     * C 端用户登录
     *
     * @param requestParam 用户登录入参
     * @return 用户登录返回信息
     */
    UserLoginAdapterRespDTO login(UserLoginAdapterRepDTO requestParam);
    
    /**
     * 检查 C 端用户是否登录
     *
     * @param token JWT Token
     * @return 用户是否登录
     */
    UserLoginAdapterRespDTO checkLogin(String token);
    
    /**
     * 获取极验配置
     *
     * @return 初始化后极验配置
     */
    GeeTestAdapterRespDTO initGeeTestConfig();
    
    /**
     * 用户退出登录
     *
     * @param accessToken 用户登录 Token
     */
    void logout(String accessToken);
}
