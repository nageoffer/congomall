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

package org.opengoofy.easymall.biz.customer.user.application.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户验证码
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@Data
@ApiModel("用户验证码")
public class UserVerifyCodeCommand {
    
    @ApiModelProperty(value = "验证类型", notes = "登录验证码，注册认证验证码等", example = "customer_user_login_verify")
    private String type;
    
    @ApiModelProperty(value = "验证平台", notes = "手机短信，邮箱，电话等", example = "mail")
    private String platform;
    
    @NotBlank(message = "接收者不能为空")
    @ApiModelProperty(value = "接收者", example = "m7798432@163.com")
    private String receiver;
}
