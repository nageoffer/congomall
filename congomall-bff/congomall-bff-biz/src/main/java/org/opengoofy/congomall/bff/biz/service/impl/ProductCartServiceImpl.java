package org.opengoofy.congomall.bff.biz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.bff.biz.assembler.ProductCartAssembler;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.bff.biz.service.ProductCartService;
import org.opengoofy.congomall.bff.remote.ProductCartRemoteService;
import org.opengoofy.congomall.bff.remote.resp.CartItemRespDTO;
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
}
