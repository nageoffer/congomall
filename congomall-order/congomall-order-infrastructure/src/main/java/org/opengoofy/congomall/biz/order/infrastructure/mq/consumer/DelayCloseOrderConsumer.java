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

package org.opengoofy.congomall.biz.order.infrastructure.mq.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.order.domain.aggregate.Order;
import org.opengoofy.congomall.biz.order.domain.common.OrderStatusEnum;
import org.opengoofy.congomall.biz.order.domain.event.DelayCloseOrderEvent;
import org.opengoofy.congomall.biz.order.domain.repository.OrderRepository;
import org.opengoofy.congomall.biz.order.infrastructure.mq.messaging.OrderSink;
import org.opengoofy.congomall.biz.order.infrastructure.remote.ProductStockRemoteService;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductStockDetailReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductUnlockStockReqDTO;
import org.opengoofy.congomall.rocketmq.springboot.starter.core.MessageWrapper;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 延迟关闭订单消费者
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@AllArgsConstructor
public class DelayCloseOrderConsumer {
    
    private final OrderRepository orderRepository;
    
    private final ProductStockRemoteService productStockRemoteService;
    
    @StreamListener(OrderSink.DELAY_CLOSE_ORDER)
    public void delayCloseOrderConsumer(MessageWrapper<DelayCloseOrderEvent> messageWrapper) {
        String orderSn = messageWrapper.getMessage().getOrderSn();
        Order order = orderRepository.findOrderByOrderSn(orderSn);
        Integer status = order.getStatus();
        if (Objects.equals(status, OrderStatusEnum.PENDING_PAYMENT.getStatus())) {
            // 关闭订单
            orderRepository.closeOrder(orderSn);
            // 解锁商品库存
            ProductUnlockStockReqDTO unlockStockReqDTO = ProductUnlockStockReqDTO.builder()
                    .productStockDetails(BeanUtil.convert(messageWrapper.getMessage().getProductSkuStockList(), ProductStockDetailReqDTO.class))
                    .orderSn(orderSn)
                    .build();
            productStockRemoteService.unlockProductStock(unlockStockReqDTO);
        }
    }
}
