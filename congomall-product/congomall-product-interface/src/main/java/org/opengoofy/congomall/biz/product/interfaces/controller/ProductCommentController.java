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
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.product.application.req.ProductCommentAppendCommand;
import org.opengoofy.congomall.biz.product.application.req.ProductCommentRemoveCommand;
import org.opengoofy.congomall.biz.product.application.req.ProductCommentSaveCommand;
import org.opengoofy.congomall.biz.product.application.service.ProductCommentService;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品评论控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@MLog
@RestController
@RequiredArgsConstructor
@Api(tags = "商品评论")
public class ProductCommentController {
    
    private final ProductCommentService productCommentService;
    
    @PostMapping("/api/product/comment")
    @ApiOperation(value = "新增商品评论", notes = "新增商品评论")
    public Result<Void> saveProductComment(@RequestBody ProductCommentSaveCommand requestParam) {
        productCommentService.saveProductComment(requestParam);
        return Results.success();
    }
    
    @DeleteMapping("/api/product/comment")
    @ApiOperation(value = "删除商品评论", notes = "删除商品评论")
    public Result<Void> removeProductComment(@RequestBody ProductCommentRemoveCommand requestParam) {
        productCommentService.removeProductComment(requestParam);
        return Results.success();
    }
    
    @PostMapping("/api/product/comment/append")
    @ApiOperation(value = "追加商品评论", notes = "买家追加商品评论")
    public Result<Void> appendProductComment(@RequestBody ProductCommentAppendCommand requestParam) {
        productCommentService.appendProductComment(requestParam);
        return Results.success();
    }
}
