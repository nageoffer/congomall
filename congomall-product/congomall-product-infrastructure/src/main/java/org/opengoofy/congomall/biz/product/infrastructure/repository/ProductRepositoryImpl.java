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

package org.opengoofy.congomall.biz.product.infrastructure.repository;

import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.opengoofy.congomall.biz.product.domain.aggregate.Product;
import org.opengoofy.congomall.biz.product.domain.aggregate.ProductPageQuery;
import org.opengoofy.congomall.biz.product.domain.aggregate.ProductStock;
import org.opengoofy.congomall.biz.product.domain.aggregate.ProductStockDetail;
import org.opengoofy.congomall.biz.product.domain.mode.ProductBrand;
import org.opengoofy.congomall.biz.product.domain.mode.ProductSku;
import org.opengoofy.congomall.biz.product.domain.mode.ProductSpu;
import org.opengoofy.congomall.biz.product.domain.repository.ProductRepository;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductBrandDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductSkuDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductSpuDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductBrandMapper;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductSkuMapper;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductSpuMapper;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * 商品仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    
    private final ProductSpuMapper productSpuMapper;
    private final ProductBrandMapper productBrandMapper;
    private final ProductSkuMapper productSkuMapper;
    
    private ThreadPoolExecutor productThreadPoolExecutor = ThreadPoolBuilder.builder()
            .threadFactory("product-executor")
            .dynamicPool()
            .build();
    ;
    
    @Override
    @SneakyThrows
    public Product getProductBySpuId(Long spuId) {
        ProductSpuDO productSpuDO = productSpuMapper.selectById(spuId);
        Future<ProductBrandDO> productBrandDOFuture = productThreadPoolExecutor
                .submit(() -> productBrandMapper.selectById(productSpuDO.getBrandId()));
        Future<List<ProductSkuDO>> productSkuDOListFuture = productThreadPoolExecutor
                .submit(() -> productSkuMapper.selectList(Wrappers.lambdaQuery(ProductSkuDO.class).eq(ProductSkuDO::getProductId, spuId)));
        return Product.builder()
                .productBrand(BeanUtil.convert(productBrandDOFuture.get(), ProductBrand.class))
                .productSpu(BeanUtil.convert(productSpuDO, ProductSpu.class))
                .productSkus(BeanUtil.convert(productSkuDOListFuture.get(), ProductSku.class))
                .build();
    }
    
    @Override
    public Boolean verifyProductStock(ProductStock productStock) {
        for (ProductStockDetail each : productStock.getProductStockDetails()) {
            LambdaQueryWrapper<ProductSkuDO> queryWrapper = Wrappers.lambdaQuery(ProductSkuDO.class)
                    .eq(ProductSkuDO::getProductId, each.getProductId())
                    .eq(ProductSkuDO::getId, each.getProductSkuId());
            ProductSkuDO productSkuDO = productSkuMapper.selectOne(queryWrapper);
            int actualStock = productSkuDO.getStock() - productSkuDO.getLockStock();
            if (actualStock - each.getProductQuantity() < 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
    
    @Override
    public Boolean lockProductStock(ProductStock productStock) {
        productStock.getProductStockDetails().forEach(each -> {
            ProductSkuDO lockStock = ProductSkuDO.builder()
                    .id(Long.parseLong(each.getProductSkuId()))
                    .productId(Long.parseLong(each.getProductId()))
                    .stock(-each.getProductQuantity())
                    .lockStock(each.getProductQuantity())
                    .build();
            int updateFlag = productSkuMapper.lockSkuStock(lockStock);
            if (updateFlag <= 0) {
                throw new ServiceException("锁定库存失败，请检查相关商品库存是否充足");
            }
        });
        return Boolean.TRUE;
    }
    
    @Override
    public Boolean unlockProductStock(ProductStock productStock) {
        productStock.getProductStockDetails().forEach(each -> {
            ProductSkuDO lockStock = ProductSkuDO.builder()
                    .id(Long.parseLong(each.getProductSkuId()))
                    .productId(Long.parseLong(each.getProductId()))
                    .stock(each.getProductQuantity())
                    .lockStock(-each.getProductQuantity())
                    .build();
            int updateFlag = productSkuMapper.unlockSkuStock(lockStock);
            if (updateFlag <= 0) {
                throw new ServiceException("解锁库存失败，请检查相关商品库存数据是否正确");
            }
        });
        return Boolean.TRUE;
    }
    
    @Override
    public PageResponse<Product> pageQueryProduct(Product product) {
        ProductPageQuery pageQuery = product.getPageQuery();
        Page<ProductSpuDO> page = new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
        LambdaQueryWrapper<ProductSpuDO> queryWrapper = Wrappers.lambdaQuery(ProductSpuDO.class)
                .between(ObjectUtil.isAllNotEmpty(pageQuery.getPriceGt(), pageQuery.getPriceLte()), ProductSpuDO::getPrice, pageQuery.getPriceGt(), pageQuery.getPriceLte())
                .orderBy(pageQuery.getSort() != null, Optional.ofNullable(pageQuery.getSort()).map(each -> each == 1 ? true : false).orElse(false), ProductSpuDO::getPrice);
        Page<ProductSpuDO> selectPage = productSpuMapper.selectPage(page, queryWrapper);
        List<Product> productList = selectPage.getRecords().stream()
                .map(each -> Product.builder().productSpu(BeanUtil.convert(each, ProductSpu.class)).build())
                .collect(Collectors.toList());
        return new PageResponse<>(pageQuery.getCurrent(), pageQuery.getSize(), selectPage.getTotal(), productList);
    }
}
