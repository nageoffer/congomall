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

package org.opengoofy.congomall.biz.bff.remote;

import org.opengoofy.congomall.biz.bff.remote.req.OrderCreateCommand;
import org.opengoofy.congomall.biz.bff.remote.resp.OrderRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单远程调用服务
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@FeignClient(value = "order-service", url = "${congomall.aggregation.remote-url:}")
public interface OrderRemoteService {
    
    /**
     * 商品订单下单
     */
    @PostMapping("/api/order")
    Result<String> createOrder(@RequestBody OrderCreateCommand requestParam);
    
    /**
     * 分页查询订单列表
     */
    @GetMapping("/api/order")
    Result<PageResponse<OrderRespDTO>> pageQueryOrder(@RequestParam("userId") String customerUserId, @RequestParam("current") Integer page, @RequestParam("size") Integer size);
    
    /**
     * 根据用户ID查询订单信息
     */
    @GetMapping("/api/order/customer-user/{customerUserId}")
    Result<List<OrderRespDTO>> getOrderByCustomerUserId(@PathVariable("customerUserId") String customerUserId);
    
    /**
     * 根据订单号查询订单信息
     */
    @GetMapping("/api/order/{orderSn}")
    Result<OrderRespDTO> getOrderByOrderSn(@PathVariable("orderSn") String orderSn);
    
    /**
     * 根据订单号删除商品订单
     */
    @DeleteMapping("/api/order/{orderSn}")
    Result<Void> deleteOrder(@PathVariable("orderSn") String orderSn);
}
