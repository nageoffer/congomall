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

package org.opengoofy.congomall.bff.web.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.bff.biz.common.ResultT;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.HomePanelAdapterRespDTO;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.HomeProductDetailAdapterRespDTO;
import org.opengoofy.congomall.bff.biz.service.GoodsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商城首页控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "商城首页")
public class GoodsController {
    
    private final GoodsService goodsService;
    
    @GetMapping("/goods/home")
    @ApiOperation(value = "商城首页板块商品数据", notes = "商城首页板块商品数据")
    public ResultT<List<HomePanelAdapterRespDTO>> goodsHome() {
        return ResultT.success(goodsService.listHomePanel());
    }
    
    @GetMapping("/goods/productDet")
    @ApiOperation(value = "商品详情", notes = "根据商品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品ID", required = true, example = "1647801029158240256")
    })
    public ResultT<HomeProductDetailAdapterRespDTO> goodsDetail(@RequestParam("productId") String productId) {
        return ResultT.success(goodsService.goodsDetail(productId));
    }
    
    @GetMapping("/goods/recommend")
    @ApiOperation(value = "为您推荐", notes = "为您推荐")
    public ResultT<List<HomePanelAdapterRespDTO>> recommend() {
        return ResultT.success(Lists.newArrayList(goodsService.recommend()));
    }
    
}
