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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.bff.common.ResultT;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.OrderCreateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderResultAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.handler.CustomBlockHandler;
import org.opengoofy.congomall.biz.bff.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.opengoofy.congomall.biz.bff.common.SentinelLimitFlowConstant.CREATE_ORDER_PATH;

/**
 * 订单控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController("bffOrderController")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService bffOrderService;
    
    @PostMapping("/member/addOrder")
    @ApiOperation(value = "订单创建", notes = "订单创建")
    @SentinelResource(
            value = CREATE_ORDER_PATH,
            blockHandler = "createOrderBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public ResultT<String> addOrder(@RequestBody OrderCreateAdapterReqDTO requestParam) {
        return ResultT.success(bffOrderService.addOrder(requestParam));
    }
    
    @GetMapping("/member/orderList")
    @ApiOperation(value = "订单列表查询", notes = "订单列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "用户商品列表第几页", required = true, example = "1"),
            @ApiImplicitParam(name = "size", value = "用户商品列表每页多少条数据", required = true, example = "10"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, example = "1634554535496892416")
    })
    public ResultT<OrderResultAdapterRespDTO> listOrder(@RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size,
                                                        @RequestParam("userId") String userId) {
        return ResultT.success(bffOrderService.listOrder(page, size, userId));
    }
    
    @GetMapping("/member/orderDetail")
    @ApiOperation(value = "订单查询", notes = "根据订单号查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, example = "1648278169705656320")
    })
    public ResultT<OrderAdapterRespDTO> getOrderDetail(@RequestParam("orderId") String orderSn) {
        return ResultT.success(bffOrderService.getOrderDetail(orderSn));
    }
    
    @GetMapping("/member/delOrder")
    @ApiOperation(value = "订单删除", notes = "根据订单号删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, example = "1648278169705656320")
    })
    public ResultT<Integer> deleteOrder(@RequestParam("orderId") String orderSn) {
        return ResultT.success(bffOrderService.deleteOrder(orderSn));
    }
}
