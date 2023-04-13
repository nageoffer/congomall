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

package org.opengoofy.congomall.biz.customer.user.application.assembler;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserLoginRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.resp.UserRegisterRespDTO;
import org.opengoofy.congomall.biz.customer.user.domain.aggregate.CustomerUser;

/**
 * C 端用户 Entity 转换 DTO
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Mapper(componentModel = "spring")
public interface CustomerUserToDTOAssembler {
    
    /**
     * C 端用户 Entity 转换用户注册返回 DTO
     */
    @Mappings({
            @Mapping(source = "customerUser.username", target = "name"),
            @Mapping(source = "customerUser.phone", target = "phone"),
            @Mapping(source = "customerUser.accountNumber", target = "accountNumber")
    })
    UserRegisterRespDTO customerUserToUserRegisterRespDTO(CustomerUser customerUser);
    
    /**
     * C 端用户 Entity 转换用户登录返回 DTO
     */
    @Mappings({
            @Mapping(source = "customerUser.customerUserId", target = "customerUserId"),
            @Mapping(source = "customerUser.username", target = "username"),
            @Mapping(source = "customerUser.accountNumber", target = "accountNumber")
    })
    UserLoginRespDTO customerUserToUserLoginRespDTO(CustomerUser customerUser);
}
