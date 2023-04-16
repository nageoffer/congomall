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

package org.opengoofy.congomall.biz.cart.application.service;

import org.opengoofy.congomall.biz.cart.application.req.*;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

import java.util.List;

/**
 * 购物车
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface CartItemService {
    
    /**
     * 分页查询购物车商品
     *
     * @param requestParam 请求入参
     * @return 购物车商品分页返回数据
     */
    PageResponse<CartItemRespDTO> pageQueryCartItem(CartItemPageQueryReqDTO requestParam);
    
    /**
     * 查询用户选中购物车商品
     *
     * @param customerUserId 用户 ID
     * @return
     */
    List<CartItemQuerySelectRespDTO> querySelectCartByCustomerUserId(String customerUserId);
    
    /**
     * 新增商品到购物车
     *
     * @param requestParam 请求入参
     */
    void addCartItem(CartItemAddReqDTO requestParam);
    
    /**
     * 修改购物车商品勾选状态
     *
     * @param requestParam 请求入参
     */
    void updateCheckCartItem(CartItemCheckUpdateReqDTO requestParam);
    
    /**
     * 修改全部购物车商品勾选状态
     *
     * @param requestParam 请求入参
     */
    void updateChecksCartItem(CartItemChecksUpdateReqDTO requestParam);
    
    /**
     * 修改购物车商品
     *
     * @param requestParam 请求入参
     */
    void updateCartItem(CartItemNumUpdateReqDTO requestParam);
    
    /**
     * 清理购物车商品
     *
     * @param requestParam 请求入参
     */
    void clearCartProduct(CartItemDelReqDTO requestParam);
    
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
     * @param requestParam 请求入参
     */
    void clearCheckCartProduct(CartItemDelCheckReqDTO requestParam);
}
