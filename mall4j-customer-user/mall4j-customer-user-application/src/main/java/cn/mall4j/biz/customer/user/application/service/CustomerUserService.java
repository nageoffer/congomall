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

package cn.mall4j.biz.customer.user.application.service;

import cn.mall4j.biz.customer.user.application.req.UserRegisterCommand;
import cn.mall4j.biz.customer.user.application.req.UserVerifyCodeCommand;
import cn.mall4j.biz.customer.user.application.resp.UserRegisterRespDTO;

/**
 * C 端用户接口
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public interface CustomerUserService {
    
    /**
     * C 端用户验证
     *
     * @param requestParam
     */
    void verifyCodeSend(UserVerifyCodeCommand requestParam);
    
    /**
     * C 端用户注册
     *
     * @param requestParam
     * @return
     */
    UserRegisterRespDTO register(UserRegisterCommand requestParam);
}
