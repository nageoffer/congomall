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

package org.opengoofy.congomall.biz.cart.domain.repository;

import org.opengoofy.congomall.biz.cart.domain.aggregate.CartItem;
import org.opengoofy.congomall.springboot.starter.convention.page.PageRequest;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

import java.util.List;

/**
 * 购物车仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface CartItemRepository {
    
    /**
     * 分页查询购物车商品
     *
     * @param customerUserId 用户 ID
     * @param pageRequest    分页请求对象
     * @return 购物车商品分页返回数据
     */
    PageResponse<CartItem> pageQueryCartItem(String customerUserId, PageRequest pageRequest);
    
    /**
     * 查询用户选中购物车商品
     *
     * @param customerUserId 用户 ID
     * @return 购物车商品集合
     */
    List<CartItem> querySelectCartByCustomerUserId(String customerUserId);
    
    /**
     * 新增商品到购物车
     *
     * @param cartItem 购物车聚合根
     */
    void addCartItem(CartItem cartItem);
    
    /**
     * 修改购物车商品勾选状态
     *
     * @param cartItem 购物车聚合根
     */
    void updateCheckCartItem(CartItem cartItem);
    
    /**
     * 修改全部购物车商品勾选状态
     *
     * @param cartItem 购物车聚合根
     */
    void updateChecksCartItem(CartItem cartItem);
    
    /**
     * 修改购物车商品
     *
     * @param cartItem 购物车聚合根
     */
    void updateCartItem(CartItem cartItem);
    
    /**
     * 删除购物车商品
     *
     * @param cartItem 购物车聚合根
     */
    void deleteCartItem(CartItem cartItem);
    
    /**
     * 统计用户购物车商品数量
     *
     * @param customerUserId 用户 ID
     * @return 统计购物车商品数量
     */
    int countUserCartItem(String customerUserId);
    
    /**
     * 删除选中购物车商品
     *
     * @param cartItem 购物车聚合根
     */
    void deleteChecksCartItem(CartItem cartItem);
}
