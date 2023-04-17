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

package org.opengoofy.congomall.biz.customer.user.infrastructure.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.customer.user.domain.mode.ReceiveAddress;
import org.opengoofy.congomall.biz.customer.user.domain.repository.ReceiveAddressRepository;
import org.opengoofy.congomall.biz.customer.user.infrastructure.dao.mapper.ReceiveAddressMapper;
import org.opengoofy.congomall.biz.customer.user.infrastructure.dao.entity.ReceiveAddressDO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户收货地址仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Repository
@AllArgsConstructor
public class ReceiveAddressRepositoryImpl implements ReceiveAddressRepository {
    
    private final ReceiveAddressMapper receiveAddressMapper;
    
    @Override
    public List<ReceiveAddress> listReceiveAddressByCustomerUserId(String customerUserId) {
        List<ReceiveAddressDO> receiveAddressDOList = receiveAddressMapper.selectList(Wrappers.lambdaQuery(ReceiveAddressDO.class).eq(ReceiveAddressDO::getCustomerUserId, customerUserId));
        return BeanUtil.convert(receiveAddressDOList, ReceiveAddress.class);
    }
    
    @Override
    public void saveReceiveAddress(ReceiveAddress receiveAddress) {
        ReceiveAddressDO receiveAddressDO = BeanUtil.convert(receiveAddress, ReceiveAddressDO.class);
        receiveAddressMapper.insert(receiveAddressDO);
    }
    
    @Override
    public void removeReceiveAddress(String customerUserId, String receiveAddressId) {
        LambdaUpdateWrapper<ReceiveAddressDO> updateWrapper = Wrappers.lambdaUpdate(ReceiveAddressDO.class)
                .eq(ReceiveAddressDO::getCustomerUserId, customerUserId)
                .eq(ReceiveAddressDO::getId, receiveAddressId);
        receiveAddressMapper.delete(updateWrapper);
    }
    
    @Override
    public void updateReceiveAddress(ReceiveAddress receiveAddress) {
        LambdaUpdateWrapper<ReceiveAddressDO> updateWrapper = Wrappers.lambdaUpdate(ReceiveAddressDO.class)
                .eq(ReceiveAddressDO::getCustomerUserId, receiveAddress.getCustomerUserId())
                .eq(ReceiveAddressDO::getId, receiveAddress.getId());
        ReceiveAddressDO receiveAddressDO = BeanUtil.convert(receiveAddress, ReceiveAddressDO.class);
        receiveAddressMapper.update(receiveAddressDO, updateWrapper);
    }
}
