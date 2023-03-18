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

package org.opengoofy.congomall.biz.customer.user.domain.aggregate;

import cn.hutool.core.util.StrUtil;
import lombok.*;
import org.opengoofy.congomall.biz.customer.user.domain.dp.*;
import org.opengoofy.congomall.biz.customer.user.domain.event.OperationLogEvent;
import org.opengoofy.congomall.biz.customer.user.domain.mode.ReceiveAddress;
import org.opengoofy.congomall.biz.customer.user.domain.toolkit.JWTUtil;
import org.opengoofy.congomall.springboot.starter.common.toolkit.EnvironmentUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ClientException;

import java.util.List;

/**
 * C 端用户实体
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class CustomerUser {
    
    private Long customerUserId;
    
    private CustomerUserName username;
    
    private CustomerUserPhone phone;
    
    private CustomerUserMail mail;
    
    private transient CustomerUserPassword password;
    
    private CustomerUserAccountNumber accountNumber;
    
    private String receiver;
    
    private String verifyCode;
    
    private OperationLogEvent operationLogEvent;
    
    private List<ReceiveAddress> receiveAddresses;
    
    public void checkoutValidCode(String verifyCode) {
        if (EnvironmentUtil.isProdEnvironment()) {
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
    
    public String generateAccessToken() {
        return JWTUtil.generateAccessToken(this);
    }
    
    public String getUsername() {
        return this.username.getUsername();
    }
    
    public String getAccountNumber() {
        return this.accountNumber.getAccountNumber();
    }
    
    public String getMail() {
        return this.mail.getMail();
    }
    
    public String getPhone() {
        return this.phone.getPhone();
    }
}
