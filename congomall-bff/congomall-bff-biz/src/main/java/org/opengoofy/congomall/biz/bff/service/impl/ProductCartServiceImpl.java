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

package org.opengoofy.congomall.biz.bff.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.bff.assembler.ProductCartAssembler;
import org.opengoofy.congomall.biz.bff.common.SelectFlagEnum;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartAddAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartDeleteChecksAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartUpdateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.ProductCartService;
import org.opengoofy.congomall.biz.bff.remote.ProductCartRemoteService;
import org.opengoofy.congomall.biz.bff.remote.ProductRemoteService;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemAddReqDTO;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemCheckUpdateReqDTO;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemChecksUpdateReqDTO;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemDelCheckReqDTO;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemDelReqDTO;
import org.opengoofy.congomall.biz.bff.remote.req.CartItemNumUpdateReqDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.CartItemRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.ProductRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.ProductSkuRespDTO;
import org.opengoofy.congomall.biz.bff.remote.resp.ProductSpuRespDTO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车接口实现层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {
    
    private final ProductCartRemoteService productCartRemoteService;
    private final ProductCartAssembler productCartAssembler;
    private final ProductRemoteService productRemoteService;
    
    private static final long PRODUCT_CART_CURRENT = 1L;
    private static final long PRODUCT_CART_SIZE = 500L;
    
    @Override
    public List<ProductCartAdapterRespDTO> listAllProductCart(String userId) {
        Result<PageResponse<CartItemRespDTO>> remoteProductResult = null;
        try {
            remoteProductResult = productCartRemoteService.pageQueryCartItem(userId, PRODUCT_CART_CURRENT, PRODUCT_CART_SIZE);
        } catch (Throwable ex) {
            log.error("调用购物车服务查询用户购物车商品失败", ex);
        }
        List<ProductCartAdapterRespDTO> result = new ArrayList<>();
        if (remoteProductResult != null && remoteProductResult.isSuccess()) {
            result = productCartAssembler.covert(remoteProductResult.getData().getRecords());
        }
        return result;
    }
    
    @Override
    public Integer addProductCard(ProductCartAddAdapterReqDTO requestParam) {
        String productId = requestParam.getProductId();
        Result<ProductRespDTO> remoteProductResult = null;
        try {
            remoteProductResult = productRemoteService.getProductBySpuId(productId);
        } catch (Throwable ex) {
            log.error("调用商品服务查询商品详细信息失败", ex);
        }
        Result<Void> addCartResult = null;
        if (remoteProductResult != null && remoteProductResult.isSuccess()) {
            ProductRespDTO productResultData = remoteProductResult.getData();
            ProductSpuRespDTO productSpu = productResultData.getProductSpu();
            ProductSkuRespDTO productSku = productResultData.getProductSkus().get(0);
            CartItemAddReqDTO cartRequestParam = BeanUtil.convert(productSpu, CartItemAddReqDTO.class);
            cartRequestParam.setProductId(String.valueOf(productSpu.getId()));
            cartRequestParam.setProductPic(productSpu.getPic());
            cartRequestParam.setProductName(productSpu.getName());
            cartRequestParam.setProductBrand(String.valueOf(productSpu.getBrandId()));
            cartRequestParam.setProductPrice(productSku.getPrice());
            cartRequestParam.setSelectFlag(SelectFlagEnum.SELECTED.getCode());
            cartRequestParam.setProductSkuId(String.valueOf(productSku.getId()));
            cartRequestParam.setCustomerUserId(requestParam.getUserId());
            cartRequestParam.setProductQuantity(requestParam.getProductNum());
            try {
                addCartResult = productCartRemoteService.addCartItem(cartRequestParam);
            } catch (Throwable ex) {
                log.error("调用购物车服务新增购物车商品失败", ex);
            }
        }
        return (addCartResult == null || !addCartResult.isSuccess()) ? 0 : 1;
    }
    
    @Override
    public Integer updateProductCard(ProductCartUpdateAdapterReqDTO requestParam) {
        Result<ProductRespDTO> remoteProductResult = null;
        try {
            remoteProductResult = productRemoteService.getProductBySpuId(requestParam.getProductId());
        } catch (Throwable ex) {
            log.error("调用商品服务查询商品详细信息失败", ex);
        }
        int updateProductCardResult = 0;
        if (remoteProductResult != null && remoteProductResult.isSuccess()) {
            try {
                ProductRespDTO productResultData = remoteProductResult.getData();
                ProductSkuRespDTO productSkuData = productResultData.getProductSkus().get(0);
                CartItemCheckUpdateReqDTO updateCheckRequestParam = new CartItemCheckUpdateReqDTO();
                updateCheckRequestParam.setProductId(requestParam.getProductId());
                updateCheckRequestParam.setProductSkuId(String.valueOf(productSkuData.getId()));
                updateCheckRequestParam.setCustomerUserId(requestParam.getUserId());
                updateCheckRequestParam.setSelectFlag(Integer.parseInt(requestParam.getChecked()));
                productCartRemoteService.updateCheckCartItem(updateCheckRequestParam);
                CartItemNumUpdateReqDTO updateCartRequestParam = new CartItemNumUpdateReqDTO();
                updateCartRequestParam.setProductId(requestParam.getProductId());
                updateCartRequestParam.setProductSkuId(String.valueOf(productSkuData.getId()));
                updateCartRequestParam.setCustomerUserId(requestParam.getUserId());
                updateCartRequestParam.setProductQuantity(requestParam.getProductNum());
                productCartRemoteService.updateNumCartItem(updateCartRequestParam);
            } catch (Throwable ex) {
                log.error("调用购物车服务修改购物车商品失败", ex);
            }
            updateProductCardResult = 1;
        }
        return updateProductCardResult;
    }
    
    @Override
    public Integer deleteProductCard(ProductCartDeleteAdapterReqDTO requestParam) {
        Result<ProductRespDTO> remoteProductResult = null;
        try {
            remoteProductResult = productRemoteService.getProductBySpuId(requestParam.getProductId());
        } catch (Throwable ex) {
            log.error("调用商品服务查询商品详细信息失败", ex);
        }
        int deleteProductCardResult = 0;
        if (remoteProductResult != null && remoteProductResult.isSuccess()) {
            try {
                ProductRespDTO productResultData = remoteProductResult.getData();
                ProductSkuRespDTO productSkuData = productResultData.getProductSkus().get(0);
                CartItemDelReqDTO delCartRequestParam = new CartItemDelReqDTO();
                delCartRequestParam.setCustomerUserId(requestParam.getUserId());
                delCartRequestParam.setProductSkuIds(Lists.newArrayList(String.valueOf(productSkuData.getId())));
                productCartRemoteService.clearCartProduct(delCartRequestParam);
            } catch (Throwable ex) {
                log.error("调用购物车服务删除购物车商品失败", ex);
            }
            deleteProductCardResult = 1;
        }
        return deleteProductCardResult;
    }
    
    @Override
    public Integer updateChecksProductCard(ProductCartChecksAdapterReqDTO requestParam) {
        CartItemChecksUpdateReqDTO checksRequestParam = new CartItemChecksUpdateReqDTO();
        checksRequestParam.setCustomerUserId(requestParam.getUserId());
        checksRequestParam.setSelectFlag(requestParam.getChecked()
                ? SelectFlagEnum.SELECTED.getCode()
                : SelectFlagEnum.UNSELECTED.getCode()
        );
        int checksProductCardResult = 0;
        try {
            productCartRemoteService.updateChecksCartItem(checksRequestParam);
            checksProductCardResult = 1;
        } catch (Throwable ex) {
            log.error("调用购物车服务全选或取消全选购物车商品失败", ex);
        }
        return checksProductCardResult;
    }
    
    @Override
    public void deleteChecksProductCard(ProductCartDeleteChecksAdapterReqDTO requestParam) {
        CartItemDelCheckReqDTO delCheckRequestParam = new CartItemDelCheckReqDTO();
        delCheckRequestParam.setCustomerUserId(requestParam.getUserId());
        try {
            productCartRemoteService.clearCheckCartProduct(delCheckRequestParam);
        } catch (Throwable ex) {
            log.error("调用购物车服务删除选中购物车商品失败", ex);
        }
    }
}
