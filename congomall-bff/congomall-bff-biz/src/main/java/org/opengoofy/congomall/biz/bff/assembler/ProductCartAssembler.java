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

package org.opengoofy.congomall.biz.bff.assembler;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.CartItemRespDTO;

import java.util.List;

/**
 * 用户购物车返回对象映射转换
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Mapper(componentModel = "spring")
public interface ProductCartAssembler {
    
    /**
     * 转换用户购物车返回实体
     */
    @Mappings({
            @Mapping(source = "productId", target = "productId"),
            @Mapping(source = "limitNum", target = "limitNum", defaultValue = "100"),
            @Mapping(source = "selectFlag", target = "checked"),
            @Mapping(source = "productPic", target = "productImg"),
            @Mapping(source = "productName", target = "productName"),
            @Mapping(source = "productQuantity", target = "productNum"),
            @Mapping(source = "productPrice", target = "salePrice")
    })
    ProductCartAdapterRespDTO covert(CartItemRespDTO requestParam);
    
    /**
     * 批量转换用户购物车返回实体
     */
    @InheritInverseConfiguration(name = "convert")
    List<ProductCartAdapterRespDTO> covert(List<CartItemRespDTO> requestParam);
}
