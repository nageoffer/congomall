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

package org.opengoofy.congomall.biz.cart.application.service.impl;

import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.cart.application.req.CartItemAddReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemCheckUpdateReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemChecksUpdateReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemDelCheckReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemDelReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemNumUpdateReqDTO;
import org.opengoofy.congomall.biz.cart.application.req.CartItemPageQueryReqDTO;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemRespDTO;
import org.opengoofy.congomall.biz.cart.application.service.CartItemService;
import org.opengoofy.congomall.biz.cart.domain.aggregate.CartItem;
import org.opengoofy.congomall.biz.cart.domain.repository.CartItemRepository;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
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
    public List<CartItemQuerySelectRespDTO> querySelectCartByCustomerUserId(String customerUserId) {
        List<CartItem> cartItems = cartItemRepository.querySelectCartByCustomerUserId(customerUserId);
        return BeanUtil.convert(cartItems, CartItemQuerySelectRespDTO.class);
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
                .selectFlag(requestParam.getSelectFlag())
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .productId(Long.parseLong(requestParam.getProductId()))
                .productSkuId(Long.parseLong(requestParam.getProductSkuId()))
                .build();
        cartItemRepository.updateCheckCartItem(cartItem);
    }
    
    @Override
    public void updateChecksCartItem(CartItemChecksUpdateReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .selectFlag(requestParam.getSelectFlag())
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .build();
        cartItemRepository.updateChecksCartItem(cartItem);
    }
    
    @Override
    public void updateCartItem(CartItemNumUpdateReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .productQuantity(requestParam.getProductQuantity())
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .productId(Long.parseLong(requestParam.getProductId()))
                .productSkuId(Long.parseLong(requestParam.getProductSkuId()))
                .build();
        cartItemRepository.updateCartItem(cartItem);
    }
    
    @Override
    public void clearCartProduct(CartItemDelReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .productSkuIds(requestParam.getProductSkuIds())
                .build();
        cartItemRepository.deleteCartItem(cartItem);
    }
    
    @Override
    public int countUserCartItem(String customerUserId) {
        return cartItemRepository.countUserCartItem(customerUserId);
    }
    
    @Override
    public void clearCheckCartProduct(CartItemDelCheckReqDTO requestParam) {
        CartItem cartItem = CartItem.builder()
                .customerUserId(Long.parseLong(requestParam.getCustomerUserId()))
                .build();
        cartItemRepository.deleteChecksCartItem(cartItem);
    }
}
