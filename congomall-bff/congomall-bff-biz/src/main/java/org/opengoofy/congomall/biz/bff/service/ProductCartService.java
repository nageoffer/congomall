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

package org.opengoofy.congomall.biz.bff.service;

import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartAddAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartUpdateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ProductCartAdapterRespDTO;

import java.util.List;

/**
 * 购物车接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface ProductCartService {
    
    /**
     * 根据用户查询所有购物车选中商品
     *
     * @param userId 用户 ID
     * @return 用户购物车返回数据
     */
    List<ProductCartAdapterRespDTO> listAllProductCart(String userId);
    
    /**
     * 添加商品到购物车
     *
     * @param requestParam 商品添加购物车请求数据
     * @return 添加购物车数量
     */
    Integer addProductCard(ProductCartAddAdapterReqDTO requestParam);
    
    /**
     * 修改商品购物车数据
     *
     * @param requestParam 修改商品购物车请求数据
     * @return 修改购物车是否成功
     */
    Integer updateProductCard(ProductCartUpdateAdapterReqDTO requestParam);
    
    /**
     * 删除商品购物车数据
     *
     * @param requestParam 删除商品购物车请求数据
     * @return 删除购物车是否成功
     */
    Integer deleteProductCard(ProductCartDeleteAdapterReqDTO requestParam);
    
    /**
     * 编辑全选商品购物车
     *
     * @param requestParam 编辑全选商品购物车请求数据
     * @return 编辑全选商品购物车是否成功
     */
    Integer updateChecksProductCard(ProductCartChecksAdapterReqDTO requestParam);
    
    /**
     * 删除选中商品购物车
     *
     * @param requestParam 删除选中商品购物车请求数据
     */
    void deleteChecksProductCard(ProductCartDeleteChecksAdapterReqDTO requestParam);
}
