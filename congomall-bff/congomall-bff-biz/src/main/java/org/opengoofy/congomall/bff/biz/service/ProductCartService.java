package org.opengoofy.congomall.bff.biz.service;

import org.opengoofy.congomall.bff.biz.dto.resp.adapter.ProductCartAdapterRespDTO;

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
}
