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

import lombok.AllArgsConstructor;
import org.opengoofy.easymall.biz.product.application.resp.ProductRespDTO;
import org.opengoofy.easymall.biz.product.application.service.ProductService;
import org.opengoofy.easymall.biz.product.domain.aggregate.Product;
import org.opengoofy.easymall.biz.product.domain.repository.ProductRepository;
import org.opengoofy.easymall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Service;

/**
 * 商品服务
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    @Override
    public ProductRespDTO getProductBySpuId(Long spuId) {
        Product product = productRepository.getProductBySpuId(spuId);
        return BeanUtil.convert(product, ProductRespDTO.class);
    }
}
