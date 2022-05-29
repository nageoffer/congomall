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

package cn.mall4j.biz.customer.user.domain.entity;

import cn.hutool.core.util.StrUtil;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserAccountNumber;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserName;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserPassword;
import cn.mall4j.biz.customer.user.domain.dp.CustomerUserPhone;
import cn.mall4j.springboot.starter.convention.exception.ClientException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

/**
 * C 端用户实体
 */
@Data
@Builder
@Setter(AccessLevel.PRIVATE)
public class CustomerUser {
    
    private CustomerUserName userName;
    
    private CustomerUserPhone phone;
    
    private CustomerUserPassword password;
    
    private CustomerUserAccountNumber accountNumber;
    
    private String receiver;
    
    private String verifyCode;
    
    public void checkoutValidCode(String verifyCode) {
        if (StrUtil.isBlank(verifyCode)) {
            throw new ClientException("验证码已失效");
        }
        verifyCode = StrUtil.trim(verifyCode);
        this.verifyCode = StrUtil.trim(this.verifyCode);
        if (!StrUtil.equals(verifyCode, this.verifyCode)) {
            throw new ClientException("验证码错误");
        }
    }
}
