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

package org.opengoofy.congomall.biz.product.application.service;

import org.opengoofy.congomall.biz.product.application.req.ProductLockStockCommand;
import org.opengoofy.congomall.biz.product.application.req.ProductPageQuery;
import org.opengoofy.congomall.biz.product.application.req.ProductStockVerifyQuery;
import org.opengoofy.congomall.biz.product.application.req.ProductUnlockStockCommand;
import org.opengoofy.congomall.biz.product.application.resp.ProductRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;

import java.util.List;

/**
 * 商品服务
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface ProductService {
    
    /**
     * 根据 spuId 查询商品信息
     *
     * @param spuId 商品 SKU ID
     * @return 商品详细信息
     */
    ProductRespDTO getProductBySpuId(Long spuId);
    
    /**
     * 验证商品库存
     *
     * @param requestParams 商品 SKU ID 以及数量集合
     * @return 商品库存是否充足，全部商品库存验证无问题返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean verifyProductStock(List<ProductStockVerifyQuery> requestParams);
    
    /**
     * 锁定商品库存
     *
     * @param requestParam 商品锁定库存入参
     * @return 是否锁定相关商品库存返回结果，锁定成功返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean lockProductStock(ProductLockStockCommand requestParam);
    
    /**
     * 解锁商品库存
     *
     * @param requestParam 商品解锁库存入参
     * @return 是否解锁相关商品库存返回结果，解锁成功返回 {@link Boolean#TRUE}，反之返回 {@link Boolean#FALSE}
     */
    Boolean unlockProductStock(ProductUnlockStockCommand requestParam);
    
    /**
     * 分页查询商品集合
     *
     * @param requestParam 分页查询参数
     * @return 分页查询商品集合返回数据
     */
    PageResponse<ProductRespDTO> pageQueryProduct(ProductPageQuery requestParam);
}
