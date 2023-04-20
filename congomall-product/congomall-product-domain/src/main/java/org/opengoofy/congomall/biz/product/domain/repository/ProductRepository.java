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

package org.opengoofy.congomall.biz.product.domain.repository;

import org.opengoofy.congomall.biz.product.domain.aggregate.Product;
import org.opengoofy.congomall.biz.product.domain.aggregate.ProductStock;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

/**
 * 商品仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface ProductRepository {
    
    /**
     * 根据 spuId 查询商品信息
     *
     * @param spuId
     * @return
     */
    Product getProductBySpuId(Long spuId);
    
    /**
     * 验证商品库存
     *
     * @param productStock 商品库存聚合根
     * @return 商品库存是否充足，全部商品库存验证无问题返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean verifyProductStock(ProductStock productStock);
    
    /**
     * 锁定商品库存
     *
     * @param productStock 商品库存聚合根
     * @return 是否锁定相关商品库存返回结果，锁定成功返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean lockProductStock(ProductStock productStock);
    
    /**
     * 解锁商品库存
     *
     * @param productStock 商品库存聚合根
     * @return 是否解锁相关商品库存返回结果，解锁成功返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean unlockProductStock(ProductStock productStock);
    
    /**
     * 分页查询商品集合
     *
     * @param product 商品聚合根
     * @return 分页查询商品集合返回数据
     */
    PageResponse<Product> pageQueryProduct(Product product);
}
