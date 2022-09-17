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

package org.opengoofy.easymall.biz.product.infrastructure.repository;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.opengoofy.easymall.biz.product.domain.aggregate.Product;
import org.opengoofy.easymall.biz.product.domain.mode.ProductBrand;
import org.opengoofy.easymall.biz.product.domain.mode.ProductSku;
import org.opengoofy.easymall.biz.product.domain.mode.ProductSpu;
import org.opengoofy.easymall.biz.product.domain.repository.ProductRepository;
import org.opengoofy.easymall.biz.product.infrastructure.dao.entity.ProductBrandDO;
import org.opengoofy.easymall.biz.product.infrastructure.dao.entity.ProductSkuDO;
import org.opengoofy.easymall.biz.product.infrastructure.dao.entity.ProductSpuDO;
import org.opengoofy.easymall.biz.product.infrastructure.dao.mapper.ProductBrandMapper;
import org.opengoofy.easymall.biz.product.infrastructure.dao.mapper.ProductSkuMapper;
import org.opengoofy.easymall.biz.product.infrastructure.dao.mapper.ProductSpuMapper;
import org.opengoofy.easymall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 商品仓储层
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Component
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    
    private final ProductSpuMapper productSpuMapper;
    
    private final ProductBrandMapper productBrandMapper;
    
    private final ProductSkuMapper productSkuMapper;
    
    private final ThreadPoolExecutor productThreadPoolExecutor;
    
    @Override
    @SneakyThrows
    public Product getProductBySpuId(Long spuId) {
        ProductSpuDO productSpuDO = productSpuMapper.selectById(spuId);
        Future<ProductBrandDO> productBrandDOFuture = productThreadPoolExecutor
                .submit(() -> productBrandMapper.selectById(productSpuDO.getBrandId()));
        Future<List<ProductSkuDO>> productSkuDOListFuture = productThreadPoolExecutor
                .submit(() -> productSkuMapper.selectList(Wrappers.lambdaQuery(ProductSkuDO.class).eq(ProductSkuDO::getProductId, spuId)));
        Product product = Product.builder()
                .productBrand(BeanUtil.convert(productBrandDOFuture.get(), ProductBrand.class))
                .productSpu(BeanUtil.convert(productSpuDO, ProductSpu.class))
                .productSkus(BeanUtil.convert(productSkuDOListFuture.get(), ProductSku.class))
                .build();
        return product;
    }
}
