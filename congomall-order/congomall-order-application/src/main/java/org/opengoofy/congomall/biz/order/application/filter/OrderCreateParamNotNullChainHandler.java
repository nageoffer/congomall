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

package org.opengoofy.congomall.biz.order.application.filter;

import org.opengoofy.congomall.biz.order.application.filter.base.OrderCreateChainFilter;
import org.opengoofy.congomall.biz.order.application.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.order.domain.common.OrderCreateErrorCodeEnum;
import org.opengoofy.congomall.springboot.starter.convention.exception.ClientException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 订单创建参数必填检验
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
public final class OrderCreateParamNotNullChainHandler implements OrderCreateChainFilter<OrderCreateCommand> {
    
    @Override
    public void handler(OrderCreateCommand requestParam) {
        if (Objects.isNull(requestParam.getCustomerUserId())) {
            throw new ClientException(OrderCreateErrorCodeEnum.CUSTOMER_USER_ID_NOTNULL);
        } else if (Objects.isNull(requestParam.getTotalAmount())) {
            throw new ClientException(OrderCreateErrorCodeEnum.TOTAL_AMOUNT_NOTNULL);
        } else if (Objects.isNull(requestParam.getPayAmount())) {
            throw new ClientException(OrderCreateErrorCodeEnum.PAY_AMOUNT_NOTNULL);
        } else if (Objects.isNull(requestParam.getFreightAmount())) {
            throw new ClientException(OrderCreateErrorCodeEnum.FREIGHT_AMOUNT_NOTNULL);
        } else if (Objects.isNull(requestParam.getSource())) {
            throw new ClientException(OrderCreateErrorCodeEnum.SOURCE_NOTNULL);
        }
        // xxx 这里应该把所有订单入参校验必填，因为重复工作量所以暂时验证上述这些
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
