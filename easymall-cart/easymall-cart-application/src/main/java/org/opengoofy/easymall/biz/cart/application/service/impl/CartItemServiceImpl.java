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

package org.opengoofy.easymall.biz.cart.application.service.impl;

import lombok.AllArgsConstructor;
import org.opengoofy.easymall.biz.cart.application.req.*;
import org.opengoofy.easymall.biz.cart.application.resp.CartItemRespDTO;
import org.opengoofy.easymall.biz.cart.application.service.CartItemService;
import org.opengoofy.easymall.biz.cart.domain.aggregate.CartItem;
import org.opengoofy.easymall.biz.cart.domain.repository.CartItemRepository;
import org.opengoofy.easymall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.easymall.springboot.starter.convention.page.PageResponse;
import org.springframework.stereotype.Service;

/**
 * 购物车
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    
    private final CartItemRepository cartItemRepository;
    
    @Override
    public PageResponse<CartItemRespDTO> pageQueryCartItem(CartItemPageQueryReqDTO requestParam) {
        PageResponse<CartItem> cartItemPageResponse = cartItemRepository.pageQueryCartItem(requestParam.getCustomerUserId(), requestParam);
        return cartItemPageResponse.convert(each -> BeanUtil.convert(each, CartItemRespDTO.class));
    }
    
    @Override
    public void addCartItem(CartItemAddReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .productName(requestParam.getProductName())
                .productId(Long.parseLong(requestParam.getProductId()))
                .productSkuId(Long.parseLong(requestParam.getProductSkuId()))
                .productBrand(requestParam.getProductBrand())
                .productAttribute(requestParam.getProductAttribute())
                .productPrice(requestParam.getProductPrice())
                .productQuantity(requestParam.getProductQuantity())
                .productPic(requestParam.getProductPic())
                .selectFlag(requestParam.getSelectFlag())
                .build();
        cartItemRepository.addCartItem(cartItem);
    }
    
    @Override
    public void updateCheckCartItem(CartItemCheckUpdateReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .id(Long.parseLong(requestParam.getId()))
                .selectFlag(requestParam.getSelectFlag())
                .build();
        cartItemRepository.updateCheckCartItem(cartItem);
    }
    
    @Override
    public void updateCartItem(CartItemNumUpdateReqDTO requestParam) {
        cartItemRepository.updateCartItem(BeanUtil.convert(requestParam, CartItem.class));
    }
    
    @Override
    public void deleteCartItem(CartItemDelReqDTO requestParam) {
        CartItem cartItem = CartItem.builder().customerUserId(Long.parseLong(requestParam.getCustomerUserId())).skuIds(requestParam.getSkuIds()).build();
        cartItemRepository.deleteCartItem(cartItem);
    }
}
