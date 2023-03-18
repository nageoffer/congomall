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

package org.opengoofy.congomall.biz.order.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.opengoofy.congomall.biz.order.domain.dto.ProductSkuStockDTO;
import org.opengoofy.congomall.ddd.framework.core.domain.DomainEvent;

import java.util.List;

/**
 * 延迟关闭订单事件
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayCloseOrderEvent implements DomainEvent {
    
    /**
     * 订单号
     */
    private String orderSn;
    
    /**
     * 参与订单的商品 SKU 以及数量，用于回退库存
     */
    private List<ProductSkuStockDTO> productSkuStockList;
}
