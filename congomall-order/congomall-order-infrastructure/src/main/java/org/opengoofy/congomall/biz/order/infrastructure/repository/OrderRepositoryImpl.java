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

package org.opengoofy.congomall.biz.order.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.order.domain.aggregate.Order;
import org.opengoofy.congomall.biz.order.domain.common.OrderStatusEnum;
import org.opengoofy.congomall.biz.order.domain.repository.OrderRepository;
import org.opengoofy.congomall.biz.order.infrastructure.dao.entity.OrderDO;
import org.opengoofy.congomall.biz.order.infrastructure.dao.entity.OrderItemDO;
import org.opengoofy.congomall.biz.order.infrastructure.dao.mapper.OrderItemMapper;
import org.opengoofy.congomall.biz.order.infrastructure.dao.mapper.OrderMapper;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.distributedid.SnowflakeIdUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单仓储层
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    
    private final OrderMapper orderMapper;
    
    private final OrderItemMapper orderItemMapper;
    
    @Override
    public Order findOrderByOrderSn(String orderSn) {
        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.lambdaQuery(OrderDO.class).eq(OrderDO::getOrderSn, orderSn);
        OrderDO orderDO = orderMapper.selectOne(queryWrapper);
        return BeanUtil.convert(orderDO, Order.class);
    }
    
    @Override
    public List<Order> findOrderByCustomerUserId(String customerUserId) {
        LambdaQueryWrapper<OrderDO> queryWrapper = Wrappers.lambdaQuery(OrderDO.class).eq(OrderDO::getCustomerUserId, customerUserId);
        List<OrderDO> orderDOList = orderMapper.selectList(queryWrapper);
        return BeanUtil.convert(orderDOList, Order.class);
    }
    
    @Override
    public void createOrder(Order order) {
        long orderId = SnowflakeIdUtil.nextId();
        OrderDO orderDO = BeanUtil.convert(order, OrderDO.class);
        orderDO.setId(orderId);
        BeanUtil.convertIgnoreNullAndBlank(order.getCneeInfo(), orderDO);
        orderMapper.insert(orderDO);
        List<OrderItemDO> orderItemDOList = BeanUtil.convert(order.getOrderProducts(), OrderItemDO.class);
        orderItemDOList.forEach(each -> each.setOrderId(orderId));
        orderItemDOList.forEach(orderItemMapper::insert);
    }
    
    @Override
    public void closeOrder(String orderSn) {
        OrderDO updateOrderDO = new OrderDO();
        updateOrderDO.setStatus(OrderStatusEnum.CLOSED.getStatus());
        LambdaUpdateWrapper<OrderDO> updateWrapper = Wrappers.lambdaUpdate(OrderDO.class).eq(OrderDO::getOrderSn, orderSn);
        orderMapper.update(updateOrderDO, updateWrapper);
    }
}
