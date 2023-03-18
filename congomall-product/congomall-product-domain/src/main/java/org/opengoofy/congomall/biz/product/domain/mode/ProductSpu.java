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

package org.opengoofy.congomall.biz.product.domain.mode;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品 SPU
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class ProductSpu {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 商品类型id
     */
    private Long categoryId;
    
    /**
     * 商品品牌id
     */
    private Long brandId;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品编码
     */
    private String productSn;
    
    /**
     * 商品主图
     */
    private String pic;
    
    /**
     * 商品图集
     */
    private String photoAlbum;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;
    
    /**
     * 促销开始时间
     */
    private Date promotionStartTime;
    
    /**
     * 促销结束时间
     */
    private Date promotionEndTime;
    
    /**
     * 副标题
     */
    private String subTitle;
    
    /**
     * 销量
     */
    private Integer sales;
    
    /**
     * 单位
     */
    private String unit;
    
    /**
     * 商品详情
     */
    private String detail;
    
    /**
     * 发布状态 0：发布 1：未发布
     */
    private Integer publishStatus;
    
    /**
     * 新品状态 0：新品 1：非新品
     */
    private Integer newStatus;
    
    /**
     * 推荐状态 0：推荐 1：非推荐
     */
    private Integer recommandStatus;
}
