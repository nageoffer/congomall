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

package org.opengoofy.congomall.biz.bff.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressSaveAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressUpdateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ReceiveAddressAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.ReceiveAddressService;
import org.opengoofy.congomall.biz.bff.remote.CustomerUserRemoteService;
import org.opengoofy.congomall.biz.bff.remote.req.ReceiveAddressSaveCommand;
import org.opengoofy.congomall.biz.bff.remote.req.ReceiveAddressUpdateCommand;
import org.opengoofy.congomall.biz.bff.remote.resp.ReceiveAddressRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户收货地址
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service("bffReceiveAddressService")
@RequiredArgsConstructor
public class ReceiveAddressServiceImpl implements ReceiveAddressService {
    
    private final CustomerUserRemoteService customerUserRemoteService;
    
    @Override
    public List<ReceiveAddressAdapterRespDTO> listReceiveAddressByCustomerUserId(String customerUserId) {
        Result<List<ReceiveAddressRespDTO>> receiveAddressRemoteResult = customerUserRemoteService.listReceiveAddress(customerUserId);
        List<ReceiveAddressAdapterRespDTO> result = new ArrayList<>();
        if (receiveAddressRemoteResult.isSuccess() && receiveAddressRemoteResult.getData() != null) {
            List<ReceiveAddressRespDTO> addressRemoteResultData = receiveAddressRemoteResult.getData();
            addressRemoteResultData.forEach(each -> {
                ReceiveAddressAdapterRespDTO resultData = new ReceiveAddressAdapterRespDTO();
                resultData.setAddressId(each.getId());
                resultData.setTel(each.getPhone());
                resultData.setUserId(each.getCustomerUserId());
                resultData.setUserName(each.getName());
                resultData.setStreetName(each.getDetailAddress());
                resultData.setIsDefault(each.getDefaultFlag() == 1 ? true : false);
                result.add(resultData);
            });
        }
        return result;
    }
    
    @Override
    public Integer saveReceiveAddress(ReceiveAddressSaveAdapterReqDTO requestParam) {
        int saveReceiveAddressFlag = 0;
        try {
            ReceiveAddressSaveCommand remoteRequestParam = new ReceiveAddressSaveCommand();
            remoteRequestParam.setName(requestParam.getUserName());
            remoteRequestParam.setDetailAddress(requestParam.getStreetName());
            remoteRequestParam.setPhone(requestParam.getTel());
            remoteRequestParam.setCustomerUserId(requestParam.getUserId());
            remoteRequestParam.setDefaultFlag(requestParam.getIsDefault() ? 1 : 0);
            customerUserRemoteService.saveReceiveAddress(remoteRequestParam);
            saveReceiveAddressFlag = 1;
        } catch (Throwable ex) {
            log.error("调用用户服务新增收货地址失败", ex);
        }
        return saveReceiveAddressFlag;
    }
    
    @Override
    public Integer removeReceiveAddress(String userId, String receiveAddressId) {
        int removeReceiveAddressFlag = 0;
        try {
            customerUserRemoteService.removeReceiveAddress(userId, receiveAddressId);
            removeReceiveAddressFlag = 1;
        } catch (Throwable ex) {
            log.error("调用用户服务删除收货地址失败", ex);
        }
        return removeReceiveAddressFlag;
    }
    
    @Override
    public Integer updateReceiveAddress(ReceiveAddressUpdateAdapterReqDTO requestParam) {
        int updateReceiveAddressFlag = 0;
        try {
            ReceiveAddressUpdateCommand remoteRequestParam = new ReceiveAddressUpdateCommand();
            remoteRequestParam.setId(requestParam.getAddressId());
            remoteRequestParam.setName(requestParam.getUserName());
            remoteRequestParam.setDetailAddress(requestParam.getStreetName());
            remoteRequestParam.setPhone(requestParam.getTel());
            remoteRequestParam.setCustomerUserId(requestParam.getUserId());
            remoteRequestParam.setDefaultFlag(requestParam.getIsDefault() ? 1 : 0);
            customerUserRemoteService.updateReceiveAddress(remoteRequestParam);
            updateReceiveAddressFlag = 1;
        } catch (Throwable ex) {
            log.error("调用用户服务修改收货地址失败", ex);
        }
        return updateReceiveAddressFlag;
    }
}
