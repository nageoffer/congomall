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

package org.opengoofy.congomall.biz.order.domain.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@AllArgsConstructor
public enum OrderStatusEnum {
    
    /**
     * 待付款：用户选好商品下单，但还未付款的状态
     */
    PENDING_PAYMENT(0),
    
    /**
     * 待发货：用户付款后，商品未发货的状态
     */
    TO_BE_DELIVERED(1),
    
    /**
     * 已发货：已经交由物流公司，订单开始更新物流信息
     */
    SHIPPED(2),
    
    /**
     * 已完成：用户确认收货，订单已完成
     */
    COMPLETED(3),
    
    /**
     * 已关闭
     */
    CLOSED(4);
    
    @Getter
    private final int status;
}
