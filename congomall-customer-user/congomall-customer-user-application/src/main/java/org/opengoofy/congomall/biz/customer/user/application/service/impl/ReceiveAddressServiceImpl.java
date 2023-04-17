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

package org.opengoofy.congomall.biz.customer.user.application.service.impl;

import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.customer.user.application.req.ReceiveAddressSaveCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.ReceiveAddressUpdateCommand;
import org.opengoofy.congomall.biz.customer.user.application.resp.ReceiveAddressRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.service.ReceiveAddressService;
import org.opengoofy.congomall.biz.customer.user.domain.mode.ReceiveAddress;
import org.opengoofy.congomall.biz.customer.user.domain.repository.ReceiveAddressRepository;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户收货地址
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@AllArgsConstructor
public class ReceiveAddressServiceImpl implements ReceiveAddressService {
    
    private final ReceiveAddressRepository receiveAddressRepository;
    
    @Override
    public List<ReceiveAddressRespDTO> listReceiveAddressByCustomerUserId(String customerUserId) {
        List<ReceiveAddress> receiveAddresses = receiveAddressRepository.listReceiveAddressByCustomerUserId(customerUserId);
        return BeanUtil.convert(receiveAddresses, ReceiveAddressRespDTO.class);
    }
    
    @Override
    public void saveReceiveAddress(ReceiveAddressSaveCommand requestParam) {
        ReceiveAddress receiveAddress = BeanUtil.convert(requestParam, ReceiveAddress.class);
        receiveAddressRepository.saveReceiveAddress(receiveAddress);
    }
    
    @Override
    public void removeReceiveAddress(String customerUserId, String receiveAddressId) {
        receiveAddressRepository.removeReceiveAddress(customerUserId, receiveAddressId);
    }
    
    @Override
    public void updateReceiveAddress(ReceiveAddressUpdateCommand requestParam) {
        ReceiveAddress receiveAddress = BeanUtil.convert(requestParam, ReceiveAddress.class);
        receiveAddressRepository.updateReceiveAddress(receiveAddress);
    }
}
