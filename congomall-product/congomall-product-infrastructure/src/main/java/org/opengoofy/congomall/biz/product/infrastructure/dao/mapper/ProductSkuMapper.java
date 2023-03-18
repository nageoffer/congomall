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

package org.opengoofy.congomall.biz.product.infrastructure.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductSkuDO;

/**
 * 商品 SKU
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface ProductSkuMapper extends BaseMapper<ProductSkuDO> {
    
    /**
     * 锁定商品 SKU 库存
     */
    int lockSkuStock(ProductSkuDO productSkuDO);
    
    /**
     * 解锁商品 SKU 库存
     */
    int unlockSkuStock(ProductSkuDO productSkuDO);
    
    /**
     * 通过流式查询的方式获取所有商品 SKU
     */
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
    @ResultType(ProductSkuDO.class)
    @Select("SELECT * FROM product_sku WHERE del_flag = '0'")
    void listAllProductSkuStreamQuery(ResultHandler<ProductSkuDO> handler);
}
