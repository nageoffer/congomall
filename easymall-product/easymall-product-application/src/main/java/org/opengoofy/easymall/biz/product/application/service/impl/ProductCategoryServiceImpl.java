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

package org.opengoofy.easymall.biz.product.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.opengoofy.easymall.biz.product.application.resp.ProductCategoryRespDTO;
import org.opengoofy.easymall.biz.product.application.service.ProductCategoryService;
import org.opengoofy.easymall.biz.product.domain.category.entity.ProductCategory;
import org.opengoofy.easymall.biz.product.domain.category.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    
    private final ProductCategoryRepository productCategoryRepository;
    
    @Override
    public List<ProductCategoryRespDTO> listAllProductCategory() {
        ProductCategory productCategory = productCategoryRepository.listAllProductCategory();
        return BeanUtil.copyToList(productCategory.getProductCategoryList(), ProductCategoryRespDTO.class);
    }
}
