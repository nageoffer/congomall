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

package org.opengoofy.congomall.biz.customer.user.domain.event;

import org.opengoofy.congomall.biz.customer.user.domain.dto.CustomerUserDTO;
import org.opengoofy.congomall.ddd.framework.core.domain.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * C 端用户操作日志事件
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@NoArgsConstructor
public class OperationLogEvent implements DomainEvent {
    
    /**
     * 之前的
     */
    private CustomerUserDTO beforeCustomerUser;
    
    /**
     * 操作后
     */
    private CustomerUserDTO afterCustomerUser;
    
    public OperationLogEvent(CustomerUserDTO afterCustomerUser) {
        this.afterCustomerUser = afterCustomerUser;
    }
    
    public OperationLogEvent(CustomerUserDTO beforeCustomerUser, CustomerUserDTO afterCustomerUser) {
        this.beforeCustomerUser = beforeCustomerUser;
        this.afterCustomerUser = afterCustomerUser;
    }
}
