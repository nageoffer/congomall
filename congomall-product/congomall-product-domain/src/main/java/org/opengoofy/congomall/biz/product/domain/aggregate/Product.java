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

package org.opengoofy.congomall.biz.product.domain.aggregate;

import lombok.*;
import org.opengoofy.congomall.biz.product.domain.mode.ProductBrand;
import org.opengoofy.congomall.biz.product.domain.mode.ProductSpu;
import org.opengoofy.congomall.biz.product.domain.mode.ProductSku;
import org.opengoofy.congomall.ddd.framework.core.domain.AggregateRoot;

import java.util.List;

/**
 * 商品信息
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取面试常问技术和视频教学
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Product implements AggregateRoot {
    
    /**
     * 商品品牌
     */
    private ProductBrand productBrand;
    
    /**
     * 商品 SPU
     */
    private ProductSpu productSpu;
    
    /**
     * 商品 SKU 集合
     */
    private List<ProductSku> productSkus;
}
