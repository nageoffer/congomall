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

package org.opengoofy.congomall.biz.customer.user.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.customer.user.application.req.ReceiveAddressSaveCommand;
import org.opengoofy.congomall.biz.customer.user.application.req.ReceiveAddressUpdateCommand;
import org.opengoofy.congomall.biz.customer.user.application.resp.ReceiveAddressRespDTO;
import org.opengoofy.congomall.biz.customer.user.application.service.ReceiveAddressService;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "收货地址")
public class ReceiveAddressController {
    
    private final ReceiveAddressService receiveAddressService;
    
    @GetMapping("/api/customer-user/receive-address/{customerUserId}")
    @ApiOperation(value = "获取用户收货地址", notes = "根据用户ID获取用户收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerUserId", value = "用户ID", required = true, example = "1634554535496892416")
    })
    public Result<List<ReceiveAddressRespDTO>> listReceiveAddress(@PathVariable("customerUserId") String customerUserId) {
        return Results.success(receiveAddressService.listReceiveAddressByCustomerUserId(customerUserId));
    }
    
    @PostMapping("/api/customer-user/receive-address")
    @ApiOperation(value = "新增用户收货地址", notes = "新增用户收货地址")
    public Result<Void> saveReceiveAddress(@RequestBody ReceiveAddressSaveCommand requestParam) {
        receiveAddressService.saveReceiveAddress(requestParam);
        return Results.success();
    }
    
    @PutMapping("/api/customer-user/receive-address")
    @ApiOperation(value = "修改用户收货地址", notes = "修改用户收货地址")
    public Result<Void> updateReceiveAddress(@RequestBody ReceiveAddressUpdateCommand requestParam) {
        receiveAddressService.updateReceiveAddress(requestParam);
        return Results.success();
    }
    
    @DeleteMapping("/api/customer-user/{customerUserId}/receive-address/{receiveAddressId}")
    @ApiOperation(value = "删除用户收货地址", notes = "根据用户ID和收货ID删除用户收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerUserId", value = "用户ID", required = true, example = "1634554535496892416"),
            @ApiImplicitParam(name = "receiveAddressId", value = "收货地址ID", required = true, example = "1634561618543894528")
    })
    public Result<Void> removeReceiveAddress(@PathVariable("customerUserId") String customerUserId, @PathVariable("receiveAddressId") String receiveAddressId) {
        receiveAddressService.removeReceiveAddress(customerUserId, receiveAddressId);
        return Results.success();
    }
}
