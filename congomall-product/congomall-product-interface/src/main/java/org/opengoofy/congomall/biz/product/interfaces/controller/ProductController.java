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

package org.opengoofy.congomall.biz.product.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.product.application.req.ProductPageQuery;
import org.opengoofy.congomall.biz.product.application.resp.ProductRespDTO;
import org.opengoofy.congomall.biz.product.application.service.ProductService;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品服务
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "商品服务")
public class ProductController {
    
    private final ProductService productService;
    
    @GetMapping("/api/product/spu/{spuId}")
    @ApiOperation(value = "查询商品详情", notes = "根据 spuId 查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "商品 spuId", required = true, example = "1477055850256982016")
    })
    public Result<ProductRespDTO> getProductBySpuId(@PathVariable("spuId") String spuId) {
        ProductRespDTO result = productService.getProductBySpuId(Long.parseLong(spuId));
        return Results.success(result);
    }
    
    @GetMapping("/api/product/page")
    @ApiOperation(value = "商品分页查询", notes = "商品分页查询返回 SPU 信息")
    public Result<PageResponse<ProductRespDTO>> pageQueryProduct(ProductPageQuery requestParam) {
        return Results.success(productService.pageQueryProduct(requestParam));
    }
}
