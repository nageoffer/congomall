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

package org.opengoofy.congomall.biz.cart.infrastructure.repository;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.cart.domain.aggregate.CartItem;
import org.opengoofy.congomall.biz.cart.domain.repository.CartItemRepository;
import org.opengoofy.congomall.biz.cart.infrastructure.dao.mapper.CartItemMapper;
import org.opengoofy.congomall.biz.cart.infrastructure.dao.entity.CartItemDO;
import org.opengoofy.congomall.mybatisplus.springboot.starter.PageUtil;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.page.PageRequest;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.springframework.stereotype.Repository;

/**
 * 购物车仓储层
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Repository
@AllArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    
    private final CartItemMapper cartItemMapper;
    
    @Override
    public PageResponse<CartItem> pageQueryCartItem(String userId, PageRequest pageRequest) {
        LambdaQueryWrapper<CartItemDO> queryWrapper = Wrappers.lambdaQuery(CartItemDO.class)
                .eq(CartItemDO::getCustomerUserId, userId);
        Page<CartItemDO> selectPage = cartItemMapper.selectPage(new Page(pageRequest.getCurrent(), pageRequest.getSize()), queryWrapper);
        return PageUtil.convert(selectPage, CartItem.class);
    }
    
    @Override
    public void addCartItem(CartItem cartItem) {
        int insertFlag = cartItemMapper.insert(BeanUtil.convert(cartItem, CartItemDO.class));
        Assert.isTrue(insertFlag > 0, () -> new ServiceException("添加购物车失败"));
    }
    
    @Override
    public void updateCheckCartItem(CartItem cartItem) {
        CartItemDO cartItemDO = BeanUtil.convert(cartItem, CartItemDO.class);
        int updateFlag = cartItemMapper.updateById(cartItemDO);
        Assert.isTrue(updateFlag > 0, () -> new ServiceException("修改购物车选中状态失败"));
    }
    
    @Override
    public void updateCartItem(CartItem cartItem) {
        CartItemDO cartItemDO = BeanUtil.convert(cartItem, CartItemDO.class);
        int updateFlag = cartItemMapper.updateById(cartItemDO);
        Assert.isTrue(updateFlag > 0, () -> new ServiceException("修改购物车失败"));
    }
    
    @Override
    public void deleteCartItem(CartItem cartItem) {
        LambdaUpdateWrapper<CartItemDO> updateWrapper = Wrappers.lambdaUpdate(CartItemDO.class)
                .eq(CartItemDO::getCustomerUserId, cartItem.getCustomerUserId())
                .in(CartItemDO::getProductSkuId, cartItem.getSkuIds());
        int updateFlag = cartItemMapper.delete(updateWrapper);
        Assert.isTrue(updateFlag > 0, () -> new ServiceException("删除购物车失败"));
    }
}
