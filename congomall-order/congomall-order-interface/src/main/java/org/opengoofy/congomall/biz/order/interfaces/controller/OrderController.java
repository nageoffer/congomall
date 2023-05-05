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

package org.opengoofy.congomall.biz.order.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.order.application.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.order.application.req.OrderPageQuery;
import org.opengoofy.congomall.biz.order.application.resp.OrderRespDTO;
import org.opengoofy.congomall.biz.order.application.service.OrderService;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.idempotent.annotation.Idempotent;
import org.opengoofy.congomall.springboot.starter.idempotent.enums.IdempotentTypeEnum;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单控制器
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "商品订单")
@RequestMapping("/api/order")
public class OrderController {
    
    private final OrderService orderService;
    
    @GetMapping("/{orderSn}")
    @ApiOperation(value = "查询订单信息", notes = "根据订单号查询订单信息")
    public Result<OrderRespDTO> getOrderByOrderSn(@PathVariable("orderSn") String orderSn) {
        OrderRespDTO result = orderService.getOrderByOrderSn(orderSn);
        return Results.success(result);
    }
    
    @GetMapping
    @ApiOperation(value = "分页查询订单列表", notes = "分页查询订单列表")
    public Result<PageResponse<OrderRespDTO>> pageQueryOrder(OrderPageQuery requestParam) {
        PageResponse<OrderRespDTO> result = orderService.pageQueryOrder(requestParam);
        return Results.success(result);
    }
    
    @GetMapping("/customer-user/{customerUserId}")
    @ApiOperation(value = "查询订单信息", notes = "根据用户ID查询订单信息")
    public Result<List<OrderRespDTO>> getOrderByCustomerUserId(@PathVariable("customerUserId") String customerUserId) {
        List<OrderRespDTO> result = orderService.getOrderByCustomerUserId(customerUserId);
        return Results.success(result);
    }
    
    @PostMapping
    @ApiOperation("商品订单下单")
    @Idempotent(
            type = IdempotentTypeEnum.PARAM,
            message = "订单已创建，请稍后再试"
    )
    public Result<String> createOrder(@RequestBody OrderCreateCommand requestParam) {
        String orderNo = orderService.createOrder(requestParam);
        return Results.success(orderNo);
    }
    
    @PutMapping("/{orderSn}")
    @ApiOperation("商品订单取消")
    @Idempotent(
            type = IdempotentTypeEnum.TOKEN,
            message = "订单取消失败，请刷新订单状态或重新操作"
    )
    public Result<Void> canalOrder(@PathVariable("orderSn") String orderSn) {
        orderService.canalOrder(orderSn);
        return Results.success();
    }
    
    @DeleteMapping("/{orderSn}")
    @ApiOperation("商品订单删除")
    @Idempotent(
            type = IdempotentTypeEnum.PARAM,
            uniqueKeyPrefix = "del_",
            message = "订单删除失败，请刷新订单状态或重新操作"
    )
    public Result<Void> deleteOrder(@PathVariable("orderSn") String orderSn) {
        orderService.deleteOrder(orderSn);
        return Results.success();
    }
}
