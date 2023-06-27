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

package org.opengoofy.congomall.biz.bff.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.Cached;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.OrderCreateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderAddressAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderGoodsAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderResultAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.OrderService;
import org.opengoofy.congomall.biz.bff.remote.OrderRemoteService;
import org.opengoofy.congomall.biz.bff.remote.ProductCartRemoteService;
import org.opengoofy.congomall.biz.bff.remote.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.bff.remote.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.OrderProductRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.OrderRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 订单接口实现层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service("bffOrderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRemoteService orderRemoteService;
    private final ProductCartRemoteService productCartRemoteService;
    
    @Override
    public String addOrder(OrderCreateAdapterReqDTO requestParam) {
        List<CartItemQuerySelectRespDTO> userCartList;
        Result<List<CartItemQuerySelectRespDTO>> selectCartListResult;
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
    
    @Override
    public OrderResultAdapterRespDTO listOrder(Integer page, Integer size, String userId) {
        Result<PageResponse<OrderRespDTO>> orderListRemoteResult;
        PageResponse<OrderRespDTO> orderPages;
        try {
            orderListRemoteResult = orderRemoteService.pageQueryOrder(userId, page, size);
            if (!orderListRemoteResult.isSuccess() || orderListRemoteResult.getData() == null) {
                throw new ServiceException("调用订单服务查询订单失败");
            }
            orderPages = orderListRemoteResult.getData();
        } catch (Throwable ex) {
            log.error("调用订单服务查询订单失败", ex);
            throw ex;
        }
        List<OrderAdapterRespDTO> orderListResult = new ArrayList<>();
        for (OrderRespDTO each : orderPages.getRecords()) {
            OrderAdapterRespDTO orderAdapter = new OrderAdapterRespDTO();
            orderAdapter.setOrderId(each.getOrderSn());
            orderAdapter.setOrderTotal(each.getTotalAmount().intValue());
            orderAdapter.setOrderStatus(String.valueOf(each.getStatus()));
            orderAdapter.setFinishDate(each.getReceiveTime());
            orderAdapter.setCreateDate(each.getCreateTime());
            OrderAddressAdapterRespDTO addressInfo = new OrderAddressAdapterRespDTO();
            addressInfo.setTel(each.getCneePhone());
            addressInfo.setStreetName(each.getCneeDetailAddress());
            addressInfo.setUserName(each.getCneeName());
            orderAdapter.setAddressInfo(addressInfo);
            List<OrderGoodsAdapterRespDTO> goodsList = new ArrayList<>();
            for (OrderProductRespDTO item : each.getOrderProducts()) {
                OrderGoodsAdapterRespDTO goods = new OrderGoodsAdapterRespDTO();
                goods.setProductId(item.getProductId());
                goods.setProductImg(item.getProductPic());
                goods.setProductName(item.getProductName());
                goods.setProductNum(item.getProductQuantity());
                goods.setSalePrice(item.getProductPrice().intValue());
                goodsList.add(goods);
            }
            orderAdapter.setGoodsList(goodsList);
            orderListResult.add(orderAdapter);
        }
        return new OrderResultAdapterRespDTO(orderPages.getTotal().intValue(), orderListResult);
    }
    
    @Override
    @Cached(name = "order:", key = "'query-order-'+#orderSn", expire = 24, timeUnit = TimeUnit.HOURS)
    public OrderAdapterRespDTO getOrderDetail(String orderSn) {
        Result<OrderRespDTO> orderDetailRemoteResult;
        OrderRespDTO orderRespDTO;
        try {
            orderDetailRemoteResult = orderRemoteService.getOrderByOrderSn(orderSn);
            if (!orderDetailRemoteResult.isSuccess() || orderDetailRemoteResult.getData() == null) {
                throw new ServiceException("调用订单服务查询详情失败");
            }
            orderRespDTO = orderDetailRemoteResult.getData();
        } catch (Throwable ex) {
            log.error("调用订单服务查询详情失败", ex);
            throw ex;
        }
        OrderAdapterRespDTO orderListResult = new OrderAdapterRespDTO();
        orderListResult.setOrderId(orderRespDTO.getOrderSn());
        orderListResult.setOrderTotal(orderRespDTO.getTotalAmount().intValue());
        orderListResult.setOrderStatus(String.valueOf(orderRespDTO.getStatus()));
        orderListResult.setFinishDate(orderRespDTO.getReceiveTime());
        orderListResult.setCreateDate(orderRespDTO.getCreateTime());
        OrderAddressAdapterRespDTO addressInfo = new OrderAddressAdapterRespDTO();
        addressInfo.setTel(orderRespDTO.getCneePhone());
        addressInfo.setStreetName(orderRespDTO.getCneeDetailAddress());
        addressInfo.setUserName(orderRespDTO.getCneeName());
        orderListResult.setAddressInfo(addressInfo);
        List<OrderGoodsAdapterRespDTO> goodsList = new ArrayList<>();
        for (OrderProductRespDTO item : orderRespDTO.getOrderProducts()) {
            OrderGoodsAdapterRespDTO goods = new OrderGoodsAdapterRespDTO();
            goods.setProductId(item.getProductId());
            goods.setProductImg(item.getProductPic());
            goods.setProductName(item.getProductName());
            goods.setProductNum(item.getProductQuantity());
            goods.setSalePrice(item.getProductPrice().intValue());
            goodsList.add(goods);
        }
        orderListResult.setGoodsList(goodsList);
        return orderListResult;
    }
    
    @Override
    @CacheInvalidate(name = "order:", key = "'query-order-'+#orderSn")
    public Integer deleteOrder(String orderSn) {
        int deleteOrderFlag = 0;
        try {
            Result<Void> deletedOrderResult = orderRemoteService.deleteOrder(orderSn);
            if (deletedOrderResult.isSuccess()) {
                deleteOrderFlag = 1;
            }
        } catch (Throwable ex) {
            log.error("调用订单服务删除订单失败", ex);
        }
        return deleteOrderFlag;
    }
}
