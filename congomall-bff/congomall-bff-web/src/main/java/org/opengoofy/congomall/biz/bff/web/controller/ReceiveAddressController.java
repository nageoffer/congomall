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

package org.opengoofy.congomall.biz.bff.web.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.bff.common.ResultT;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressDeleteAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressQueryAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressSaveAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressUpdateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ReceiveAddressAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.handler.CustomBlockHandler;
import org.opengoofy.congomall.biz.bff.service.ReceiveAddressService;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.opengoofy.congomall.biz.bff.common.SentinelLimitFlowConstant.ADD_ADDRESS_PATH;

/**
 * 收货地址控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController(value = "bffReceiveAddressController")
@AllArgsConstructor
@Api(tags = "收货地址")
public class ReceiveAddressController {
    
    private final ReceiveAddressService bffReceiveAddressService;
    
    @PostMapping("/member/addressList")
    @ApiOperation(value = "获取用户收货地址", notes = "根据用户ID获取用户收货地址")
    public ResultT<List<ReceiveAddressAdapterRespDTO>> listReceiveAddress(@RequestBody ReceiveAddressQueryAdapterReqDTO requestParam) {
        return ResultT.success(bffReceiveAddressService.listReceiveAddressByCustomerUserId(requestParam.getUserId()));
    }
    
    @PostMapping("/member/addAddress")
    @ApiOperation(value = "新增用户收货地址", notes = "新增用户收货地址")
    @SentinelResource(
            value = ADD_ADDRESS_PATH,
            blockHandler = "addAddressBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public ResultT<Integer> saveReceiveAddress(@RequestBody ReceiveAddressSaveAdapterReqDTO requestParam) {
        return ResultT.success(bffReceiveAddressService.saveReceiveAddress(requestParam));
    }
    
    @PostMapping("/member/updateAddress")
    @ApiOperation(value = "修改用户收货地址", notes = "修改用户收货地址")
    public ResultT<Integer> updateReceiveAddress(@RequestBody ReceiveAddressUpdateAdapterReqDTO requestParam) {
        return ResultT.success(bffReceiveAddressService.updateReceiveAddress(requestParam));
    }
    
    @PostMapping("/member/delAddress")
    @ApiOperation(value = "删除用户收货地址", notes = "根据用户ID和收货ID删除用户收货地址")
    public ResultT<Integer> removeReceiveAddress(@RequestBody ReceiveAddressDeleteAdapterReqDTO requestParam) {
        return ResultT.success(bffReceiveAddressService.removeReceiveAddress(requestParam.getUserId(), requestParam.getAddressId()));
    }
}
