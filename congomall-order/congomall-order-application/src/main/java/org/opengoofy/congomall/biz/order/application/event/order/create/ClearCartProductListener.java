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

package org.opengoofy.congomall.biz.order.application.event.order.create;

import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.order.domain.aggregate.OrderProduct;
import org.opengoofy.congomall.biz.order.infrastructure.remote.CartRemoteService;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.CartItemDelReqDTO;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * 清理购物车商品监听
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Order(1)
@Component
@RequiredArgsConstructor
public final class ClearCartProductListener implements ApplicationListener<OrderCreateEvent> {
    
    private final CartRemoteService cartRemoteService;
    
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        CartItemDelReqDTO cartItemDelReqDTO = new CartItemDelReqDTO();
        cartItemDelReqDTO.setCustomerUserId(String.valueOf(event.getOrder().getCustomerUserId()));
        cartItemDelReqDTO.setProductSkuIds(event.getOrder().getOrderProducts().stream().map(OrderProduct::getProductSkuId).map(String::valueOf).collect(Collectors.toList()));
        cartRemoteService.clearCartProduct(cartItemDelReqDTO);
    }
}
