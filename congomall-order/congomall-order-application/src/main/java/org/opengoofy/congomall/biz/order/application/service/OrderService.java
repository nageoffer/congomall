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

package org.opengoofy.congomall.biz.order.application.service;

import org.opengoofy.congomall.biz.order.application.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.order.application.req.OrderPageQuery;
import org.opengoofy.congomall.biz.order.application.resp.OrderRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

import java.util.List;

/**
 * 订单接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface OrderService {
    
    /**
     * 创建商品订单
     *
     * @param requestParam 商品订单入参
     * @return 订单号
     */
    String createOrder(OrderCreateCommand requestParam);
    
    /**
     * 查询订单信息
     *
     * @param orderSn 订单号
     * @return 订单基本信息
     */
    OrderRespDTO getOrderByOrderSn(String orderSn);
    
    /**
     * 查询订单信息
     *
     * @param customerUserId 用户 ID
     * @return 用户订单信息集合
     */
    List<OrderRespDTO> getOrderByCustomerUserId(String customerUserId);
    
    /**
     * 取消订单
     *
     * @param orderSn 订单号
     */
    void canalOrder(String orderSn);
    
    /**
     * 删除订单
     *
     * @param orderSn 订单号
     */
    void deleteOrder(String orderSn);
    
    /**
     * 分页查询订单列表
     *
     * @param requestParam 分页查询订单列表入参
     * @return 分页查询订单列表返回数据
     */
    PageResponse<OrderRespDTO> pageQueryOrder(OrderPageQuery requestParam);
}
