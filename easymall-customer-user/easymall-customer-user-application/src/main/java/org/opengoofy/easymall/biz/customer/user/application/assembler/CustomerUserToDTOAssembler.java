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

package org.opengoofy.easymall.biz.customer.user.application.assembler;

import org.opengoofy.easymall.biz.customer.user.application.resp.UserRegisterRespDTO;
import org.opengoofy.easymall.biz.customer.user.domain.entity.CustomerUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * C 端用户 Entity 转换 DTO
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@Mapper(componentModel = "spring")
public interface CustomerUserToDTOAssembler {
    
    /**
     * C 端用户 Entity 转换用户注册返回 DTO
     *
     * @param customerUser
     * @return
     */
    @Mappings({
            @Mapping(source = "customerUser.username", target = "name"),
            @Mapping(source = "customerUser.phone", target = "phone"),
            @Mapping(source = "customerUser.accountNumber", target = "accountNumber")
    })
    UserRegisterRespDTO customerUserToUserRegisterRespDTO(CustomerUser customerUser);
}
