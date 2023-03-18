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

import cn.hutool.core.bean.BeanUtil;
import org.opengoofy.congomall.biz.product.domain.dto.ProductCategoryDTO;
import org.opengoofy.congomall.biz.product.domain.mode.ProductCategory;
import org.opengoofy.congomall.biz.product.domain.repository.ProductCategoryRepository;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductCategoryDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductCategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品分类仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
@AllArgsConstructor
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    
    private final ProductCategoryMapper productCategoryMapper;
    
    @Override
    public ProductCategory listAllProductCategory() {
        LambdaQueryWrapper<ProductCategoryDO> queryWrapper = Wrappers.lambdaQuery(ProductCategoryDO.class).eq(ProductCategoryDO::getStatus, 0);
        List<ProductCategoryDO> productCategoryDOS = productCategoryMapper.selectList(queryWrapper);
        return ProductCategory.builder().productCategoryList(BeanUtil.copyToList(productCategoryDOS, ProductCategoryDTO.class)).build();
    }
}
