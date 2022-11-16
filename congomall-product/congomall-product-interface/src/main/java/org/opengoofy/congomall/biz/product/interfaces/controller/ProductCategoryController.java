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

import org.opengoofy.congomall.biz.product.application.resp.ProductCategoryRespDTO;
import org.opengoofy.congomall.biz.product.application.service.ProductCategoryService;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "商品分类")
@RequestMapping("/product/category")
public class ProductCategoryController {
    
    private final ProductCategoryService productCategoryService;
    
    @ApiOperation(value = "查询商品分类集合", notes = "返回全部分类")
    @GetMapping("/list/all")
    public Result<List<ProductCategoryRespDTO>> listAllProductCategory() {
        return Results.success(productCategoryService.listAllProductCategory());
    }
}
