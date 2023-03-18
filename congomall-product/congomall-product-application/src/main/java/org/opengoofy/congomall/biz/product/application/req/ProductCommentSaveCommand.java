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

package org.opengoofy.congomall.biz.product.application.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品评论新增
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class ProductCommentSaveCommand {
    
    @ApiModelProperty("上级id，一级评论为0")
    private String parentId;
    
    @ApiModelProperty("商品id")
    private String productId;
    
    @ApiModelProperty("商品sku id")
    private String productSkuId;
    
    @ApiModelProperty("用户id")
    private String customerUserId;
    
    @ApiModelProperty("评分")
    private Integer star;
    
    @ApiModelProperty("评论")
    private String content;
    
    @ApiModelProperty("回复标识 0：用户 1：店家")
    private Integer commentFlag;
    
    @ApiModelProperty("匿名标识 0：匿名 1：不匿名")
    private Integer hideFlag;
    
    @ApiModelProperty("追加标识 0：否 1：是")
    private Integer appendFlag;
    
    @ApiModelProperty("评论图片/视频，json格式")
    private String resource;
}
