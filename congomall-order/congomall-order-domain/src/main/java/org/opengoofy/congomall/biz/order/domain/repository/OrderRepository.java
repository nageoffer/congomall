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

package org.opengoofy.congomall.biz.order.domain.repository;

import org.opengoofy.congomall.biz.order.domain.aggregate.Order;
import org.opengoofy.congomall.springboot.starter.convention.page.PageRequest;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

import java.util.List;

/**
 * 订单仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface OrderRepository {
    
    /**
     * 根据订单号查询订单
     *
     * @param orderSn 订单号
     * @return 订单聚合根
     */
    Order findOrderByOrderSn(String orderSn);
    
    /**
     * 根据用户 ID 查询订单
     *
     * @param customerUserId 用户 ID
     * @return 订单聚合根集合
     */
    List<Order> findOrderByCustomerUserId(String customerUserId);
    
    /**
     * 订单创建
     *
     * @param order 订单聚合根
     */
    void createOrder(Order order);
    
    /**
     * 关闭订单
     *
     * @param orderSn 订单号
     */
    void closeOrder(String orderSn);
    
    /**
     * 取消订单
     *
     * @param orderSn 订单号
     */
    void canalOrder(String orderSn);
    
    /**
     * 订单状态反转
     *
     * @param order 订单聚合根
     */
    void statusReversal(Order order);
    
    /**
     * 删除订单
     *
     * @param orderSn 订单号
     */
    void deleteOrder(String orderSn);
    
    /**
     * 分页查询订单列表
     *
     * @param userId      用户 ID
     * @param pageRequest 分页配置
     * @return 分页查询订单列表返回信息
     */
    PageResponse<Order> pageQueryOrder(String userId, PageRequest pageRequest);
}
