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

package org.opengoofy.congomall.biz.order.application.service.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.order.application.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.order.application.service.OrderService;
import org.opengoofy.congomall.biz.order.domain.aggregate.CneeInfo;
import org.opengoofy.congomall.biz.order.domain.aggregate.Order;
import org.opengoofy.congomall.biz.order.domain.aggregate.OrderProduct;
import org.opengoofy.congomall.biz.order.domain.common.OrderStatusEnum;
import org.opengoofy.congomall.biz.order.domain.dto.ProductSkuStockDTO;
import org.opengoofy.congomall.biz.order.domain.event.DelayCloseOrderEvent;
import org.opengoofy.congomall.biz.order.domain.repository.OrderRepository;
import org.opengoofy.congomall.biz.order.infrastructure.mq.provide.DelayCloseOrderProvide;
import org.opengoofy.congomall.biz.order.infrastructure.remote.CartRemoteService;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.CartItemDelReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.distributedid.SnowflakeIdUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单接口
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final CartRemoteService cartRemoteService;
    
    private final OrderRepository orderRepository;
    
    private final DelayCloseOrderProvide delayCloseOrderProvide;
    
    @Override
    public String createOrder(OrderCreateCommand requestParam) {
        // 创建订单号
        String orderSn = SnowflakeIdUtil.nextIdStr();
        // 调用购物车服务获取已选中参与结算商品
        List<CartItemQuerySelectRespDTO> cartProducts = querySelectCartByCustomerUserId(requestParam.getCustomerUserId());
        List<OrderProduct> orderProducts = cartProducts.stream()
                .map(each -> BeanUtil.convert(each, OrderProduct.class).setOrderSn(orderSn))
                .collect(Collectors.toList());
        // 构建订单聚合根
        Order order = Order.builder()
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .orderSn(orderSn)
                .totalAmount(requestParam.getTotalAmount())
                .payAmount(requestParam.getPayAmount())
                .freightAmount(requestParam.getFreightAmount())
                .source(requestParam.getSource())
                .type(requestParam.getType())
                .cneeInfo(BeanUtil.convert(requestParam, CneeInfo.class))
                .remark(requestParam.getRemark())
                .status(OrderStatusEnum.PENDING_PAYMENT.getStatus())
                .orderProducts(orderProducts)
                .build();
        // 创建订单
        orderRepository.createOrder(order);
        // 清理购物车商品
        clearCartProduct(cartProducts);
        // TODO 锁定库存
        // 发送延迟队列取消未付款订单
        delayCloseOrderProvide.delayCloseOrderSend(
                new DelayCloseOrderEvent(
                        orderSn,
                        cartProducts.stream().map(each -> new ProductSkuStockDTO(each.getProductSkuId(), each.getProductQuantity())).collect(Collectors.toList())));
        return orderSn;
    }
    
    /**
     * 根据用户ID查询选中状态购物车商品
     *
     * @param customerUserId 用户ID
     * @return 用户购物车选中商品，参与订单结算
     */
    private List<CartItemQuerySelectRespDTO> querySelectCartByCustomerUserId(String customerUserId) {
        Result<List<CartItemQuerySelectRespDTO>> cartProductsResult = cartRemoteService.querySelectCartByCustomerUserId(customerUserId);
        return Optional.ofNullable(cartProductsResult)
                .filter(each -> each.isSuccess())
                .filter(each -> CollUtil.isNotEmpty(each.getData()))
                .map(each -> each.getData())
                .orElseThrow(() -> new ServiceException("购物车选中商品为空"));
    }
    
    /**
     * 删除用户订单中购物车商品
     *
     * @param cartProducts 购物车商品集合
     */
    private void clearCartProduct(List<CartItemQuerySelectRespDTO> cartProducts) {
        CartItemDelReqDTO cartItemDelReqDTO = new CartItemDelReqDTO();
        cartItemDelReqDTO.setCustomerUserId(cartProducts.get(0).getCustomerUserId());
        cartItemDelReqDTO.setSkuIds(cartProducts.stream().map(CartItemQuerySelectRespDTO::getProductSkuId).collect(Collectors.toList()));
        cartRemoteService.clearCartProduct(cartItemDelReqDTO);
    }
}
