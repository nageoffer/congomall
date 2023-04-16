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

package org.opengoofy.congomall.biz.cart.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.cart.application.req.*;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.biz.cart.application.resp.CartItemRespDTO;
import org.opengoofy.congomall.biz.cart.application.service.CartItemService;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车控制器
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "购物车")
@RequestMapping("/api/cart/product")
public class CartItemController {
    
    private final CartItemService cartItemService;
    
    @GetMapping("/page")
    @ApiOperation(value = "分页查询购物车商品")
    public Result<PageResponse<CartItemRespDTO>> pageQueryCartItem(CartItemPageQueryReqDTO requestParam) {
        PageResponse<CartItemRespDTO> resultPage = cartItemService.pageQueryCartItem(requestParam);
        return Results.success(resultPage);
    }
    
    @GetMapping("/{customerUserId}")
    @ApiOperation(value = "查询用户选中购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerUserId", value = "用户ID", required = true, example = "1634554535496892416")
    })
    public Result<List<CartItemQuerySelectRespDTO>> querySelectCartByCustomerUserId(@PathVariable("customerUserId") String customerUserId) {
        List<CartItemQuerySelectRespDTO> result = cartItemService.querySelectCartByCustomerUserId(customerUserId);
        return Results.success(result);
    }
    
    @PostMapping
    @ApiOperation(value = "新增商品到购物车")
    public Result<Void> addCartItem(@RequestBody CartItemAddReqDTO requestParam) {
        cartItemService.addCartItem(requestParam);
        return Results.success();
    }
    
    @PutMapping("/check")
    @ApiOperation(value = "修改购物车商品勾选状态")
    public Result<Void> updateCheckCartItem(@RequestBody CartItemCheckUpdateReqDTO requestParam) {
        cartItemService.updateCheckCartItem(requestParam);
        return Results.success();
    }
    
    @PutMapping("/checks")
    @ApiOperation(value = "修改全部购物车商品勾选状态")
    public Result<Void> updateChecksCartItem(@RequestBody CartItemChecksUpdateReqDTO requestParam) {
        cartItemService.updateChecksCartItem(requestParam);
        return Results.success();
    }
    
    @PutMapping("/num")
    @ApiOperation(value = "修改购物车商品SKU数量")
    public Result<Void> updateNumCartItem(@RequestBody CartItemNumUpdateReqDTO requestParam) {
        cartItemService.updateCartItem(requestParam);
        return Results.success();
    }
    
    @DeleteMapping
    @ApiOperation(value = "删除购物车商品")
    public Result<Void> clearCartProduct(@RequestBody CartItemDelReqDTO requestParam) {
        cartItemService.clearCartProduct(requestParam);
        return Results.success();
    }
    
    @DeleteMapping("/delete/checks")
    @ApiOperation(value = "删除选中购物车商品")
    public Result<Void> clearCheckCartProduct(@RequestBody CartItemDelCheckReqDTO requestParam) {
        cartItemService.clearCheckCartProduct(requestParam);
        return Results.success();
    }
    
    @GetMapping("/count/{customerUserId}")
    @ApiOperation(value = "统计用户购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerUserId", value = "用户ID", required = true, example = "1634554535496892416")
    })
    public Result<Integer> countUserCartItem(@PathVariable("customerUserId") String customerUserId) {
        int result = cartItemService.countUserCartItem(customerUserId);
        return Results.success(result);
    }
}
