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

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.product.domain.aggregate.ProductComment;
import org.opengoofy.congomall.biz.product.domain.repository.ProductCommentRepository;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductCommentDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductCommentMapper;
import org.opengoofy.congomall.springboot.starter.common.enums.FlagEnum;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Repository;

/**
 * 商品评论仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Repository
@RequiredArgsConstructor
public class ProductCommentRepositoryImpl implements ProductCommentRepository {
    
    private final ProductCommentMapper productCommentMapper;
    
    @Override
    public void saveProductComment(ProductComment productComment) {
        ProductCommentDO productCommentDO = BeanUtil.convert(productComment, ProductCommentDO.class);
        productCommentMapper.insert(productCommentDO);
    }
    
    @Override
    public void removeProductComment(ProductComment productComment) {
        LambdaUpdateWrapper<ProductCommentDO> updateWrapper = Wrappers.lambdaUpdate(ProductCommentDO.class)
                .eq(ProductCommentDO::getProductId, productComment.getProductId())
                .eq(ProductCommentDO::getId, productComment.getId());
        productCommentMapper.delete(updateWrapper);
    }
    
    @Override
    public void appendProductComment(ProductComment productComment) {
        ProductCommentDO productCommentDO = BeanUtil.convert(productComment, ProductCommentDO.class);
        productCommentDO.setAppendFlag(FlagEnum.TRUE.code());
        productCommentDO.setCommentFlag(FlagEnum.FALSE.code());
        productCommentMapper.insert(productCommentDO);
    }
}
