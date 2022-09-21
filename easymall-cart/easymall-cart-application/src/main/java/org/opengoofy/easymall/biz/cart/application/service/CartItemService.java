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

package org.opengoofy.easymall.biz.cart.application.service;

import org.opengoofy.easymall.biz.cart.application.req.*;
import org.opengoofy.easymall.biz.cart.application.resp.CartItemRespDTO;
import org.opengoofy.easymall.springboot.starter.convention.page.PageResponse;

/**
 * 购物车
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
public interface CartItemService {
    
    /**
     * 分页查询购物车商品
     *
     * @param requestParam
     * @return
     */
    PageResponse<CartItemRespDTO> pageQueryCartItem(CartItemPageQueryReqDTO requestParam);
    
    /**
     * 新增商品到购物车
     *
     * @param requestParam
     */
    void addCartItem(CartItemAddReqDTO requestParam);
    
    /**
     * 修改购物车商品勾选状态
     *
     * @param requestParam
     */
    void updateCheckCartItem(CartItemCheckUpdateReqDTO requestParam);
    
    /**
     * 修改购物车商品
     *
     * @param requestParam
     */
    void updateCartItem(CartItemNumUpdateReqDTO requestParam);
    
    /**
     * 删除购物车商品
     *
     * @param requestParam
     */
    void deleteCartItem(CartItemDelReqDTO requestParam);
}
