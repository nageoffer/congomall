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
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.bff.common.ResultT;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartAddAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartUpdateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.handler.CustomBlockHandler;
import org.opengoofy.congomall.biz.bff.service.ProductCartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.opengoofy.congomall.biz.bff.common.SentinelLimitFlowConstant.ADD_CART_PATH;

/**
 * 购物车控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "商品购物车")
public class ProductCartController {
    
    private final ProductCartService productCartService;
    
    @PostMapping("/member/cartList")
    @ApiOperation(value = "查询用户购物车", notes = "根据用户ID查询购物车数据")
    public ResultT<List<ProductCartAdapterRespDTO>> listAllProductCart(@RequestBody ProductCartAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.listAllProductCart(requestParam.getUserId()));
    }
    
    @PostMapping("/member/addCart")
    @ApiOperation(value = "新增购物车", notes = "新增购物车")
    @SentinelResource(
            value = ADD_CART_PATH,
            blockHandler = "addCardBlockHandlerMethod",
            blockHandlerClass = CustomBlockHandler.class
    )
    public ResultT<Integer> addProductCard(@RequestBody ProductCartAddAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.addProductCard(requestParam));
    }
    
    @PostMapping("/member/cartEdit")
    @ApiOperation(value = "编辑购物车", notes = "编辑购物车商品数量")
    public ResultT<Integer> updateProductCard(@RequestBody ProductCartUpdateAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.updateProductCard(requestParam));
    }
    
    @PostMapping("/member/cartDel")
    @ApiOperation(value = "删除购物车", notes = "删除购物车商品")
    public ResultT<Integer> deleteProductCard(@RequestBody ProductCartDeleteAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.deleteProductCard(requestParam));
    }
    
    @PostMapping("/member/editCheckAll")
    @ApiOperation(value = "编辑全选购物车", notes = "编辑全选购物车商品")
    public ResultT<Integer> updateChecksProductCard(@RequestBody ProductCartChecksAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.updateChecksProductCard(requestParam));
    }
    
    @PostMapping("/member/delCartChecked")
    @ApiOperation(value = "删除选中购物车", notes = "删除选中购物车商品")
    public ResultT<Void> deleteChecksProductCard(@RequestBody ProductCartDeleteChecksAdapterReqDTO requestParam) {
        productCartService.deleteChecksProductCard(requestParam);
        return ResultT.success();
    }
}
