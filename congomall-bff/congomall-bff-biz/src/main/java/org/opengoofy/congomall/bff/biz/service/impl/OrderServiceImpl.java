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

package org.opengoofy.congomall.bff.biz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.bff.biz.dto.req.adapter.OrderCreateAdapterReqDTO;
import org.opengoofy.congomall.bff.biz.service.OrderService;
import org.opengoofy.congomall.bff.remote.OrderRemoteService;
import org.opengoofy.congomall.bff.remote.ProductCartRemoteService;
import org.opengoofy.congomall.bff.remote.req.OrderCreateCommand;
import org.opengoofy.congomall.bff.remote.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单接口实现层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRemoteService orderRemoteService;
    private final ProductCartRemoteService productCartRemoteService;
    
    @Override
    public String addOrder(OrderCreateAdapterReqDTO requestParam) {
        List<CartItemQuerySelectRespDTO> userCartList;
        Result<List<CartItemQuerySelectRespDTO>> selectCartListResult = null;
        try {
            selectCartListResult = productCartRemoteService.querySelectCartByCustomerUserId(requestParam.getUserId());
            if (!selectCartListResult.isSuccess() || selectCartListResult.getData() == null) {
                throw new ServiceException("调用购物车服务查询用户选中商品失败");
            }
            userCartList = selectCartListResult.getData();
        } catch (Throwable ex) {
            log.error("调用购物车服务查询用户选中商品失败", ex);
            throw ex;
        }
        BigDecimal amount = new BigDecimal("0");
        for (CartItemQuerySelectRespDTO each : userCartList) {
            if (each.getProductPrice() != null) {
                amount = amount.add(each.getProductPrice());
            }
        }
        OrderCreateCommand orderCreateRemoteRequestParam = new OrderCreateCommand();
        orderCreateRemoteRequestParam.setCustomerUserId(requestParam.getUserId());
        orderCreateRemoteRequestParam.setTotalAmount(amount);
        orderCreateRemoteRequestParam.setPayAmount(amount);
        orderCreateRemoteRequestParam.setFreightAmount(new BigDecimal("0"));
        orderCreateRemoteRequestParam.setSource(0);
        orderCreateRemoteRequestParam.setType(0);
        orderCreateRemoteRequestParam.setCneeName(requestParam.getUserName());
        orderCreateRemoteRequestParam.setCneePhone(requestParam.getTel());
        orderCreateRemoteRequestParam.setCneeDetailAddress(requestParam.getStreetName());
        Result<String> orderCreateRemoteResult;
        try {
            orderCreateRemoteResult = orderRemoteService.createOrder(orderCreateRemoteRequestParam);
            if (!orderCreateRemoteResult.isSuccess() || orderCreateRemoteResult.getData() == null) {
                throw new ServiceException("调用订单服务创建订单失败");
            }
        } catch (Throwable ex) {
            log.error("调用订单服务创建订单失败", ex);
            throw ex;
        }
        return orderCreateRemoteResult.getData();
    }
}
