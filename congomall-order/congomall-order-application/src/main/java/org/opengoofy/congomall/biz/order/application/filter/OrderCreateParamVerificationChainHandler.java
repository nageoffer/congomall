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

import java.math.BigDecimal;

/**
 * 订单创建参数正确性检验
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
public final class OrderCreateParamVerificationChainHandler implements OrderCreateChainFilter<OrderCreateCommand> {
    
    @Override
    public void handler(OrderCreateCommand requestParam) {
        if (requestParam.getTotalAmount().compareTo(new BigDecimal(0)) == -1 || requestParam.getTotalAmount().compareTo(new BigDecimal(0)) == 0) {
            throw new ClientException(OrderCreateErrorCodeEnum.TOTAL_AMOUNT_ERROR);
        } else if (requestParam.getPayAmount().compareTo(new BigDecimal(0)) == -1 || requestParam.getPayAmount().compareTo(new BigDecimal(0)) == 0) {
            throw new ClientException(OrderCreateErrorCodeEnum.PAY_AMOUNT_ERROR);
        } else if (requestParam.getFreightAmount().compareTo(new BigDecimal(0)) == -1) {
            throw new ClientException(OrderCreateErrorCodeEnum.FREIGHT_AMOUNT_ERROR);
        } else if (requestParam.getTotalAmount().compareTo(requestParam.getPayAmount()) == -1) {
            throw new ClientException(OrderCreateErrorCodeEnum.AMOUNT_VERIFICATION_ERROR);
        }
        // xxx 还有更多订单参数信息需要验证，因为重复工作量所以暂时验证上述这些
    }
    
    @Override
    public int getOrder() {
        return 1;
    }
}
