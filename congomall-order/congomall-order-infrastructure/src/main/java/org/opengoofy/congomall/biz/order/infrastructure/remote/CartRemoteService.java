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

package org.opengoofy.congomall.biz.order.infrastructure.remote;

import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.CartItemDelReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 购物车远程调用
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@FeignClient(value = "cart-service", url = "${congomall.aggregation.remote-url:}")
public interface CartRemoteService {
    
    /**
     * 根据用户ID查询选中状态购物车商品
     */
    @GetMapping("/api/cart/product/{customer_user_id}")
    Result<List<CartItemQuerySelectRespDTO>> querySelectCartByCustomerUserId(@PathVariable("customer_user_id") String customerUserId);
    
    /**
     * 删除购物车商品
     */
    @DeleteMapping("/api/cart/product")
    Result<Void> clearCartProduct(@RequestBody CartItemDelReqDTO requestParam);
}
