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

package org.opengoofy.congomall.biz.customer.user.application.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
 * 用户登录
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@ApiModel("用户登录")
public class UserLoginCommand {
    
    @ApiModelProperty(value = "登录方式", notes = "邮箱验证码、账号密码、手机号验证码、微信二维码等", example = "UserLoginTypeEnum.USER_LOGIN_MAIL")
    private String loginType;
    
    @ApiModelProperty(value = "账号", example = "15601166692")
    private String accountNumber;
    
    @ApiModelProperty(value = "密码", example = "xiao-ma-ge")
    private String password;
    
    @ApiModelProperty(value = "手机号", example = "15601166692")
    private String phone;
    
    @ApiModelProperty(value = "邮箱", example = "m7798432@163.com", notes = "实际发送时更改为自己邮箱")
    @Email
    private String mail;
    
    @ApiModelProperty(value = "邮箱验证码", example = "123456")
    private String mailValidCode;
}
