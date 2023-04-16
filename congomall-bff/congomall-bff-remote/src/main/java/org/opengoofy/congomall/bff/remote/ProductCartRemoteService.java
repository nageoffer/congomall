package org.opengoofy.congomall.bff.remote;

import org.opengoofy.congomall.bff.remote.resp.CartItemQuerySelectRespDTO;
import org.opengoofy.congomall.bff.remote.resp.CartItemRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品购物车远程调用服务
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@FeignClient("cart-service")
public interface ProductCartRemoteService {
    
    /**
     * 分页查询购物车商品
     */
    @GetMapping("/api/cart/product/page")
    Result<PageResponse<CartItemRespDTO>> pageQueryCartItem(@RequestParam("customerUserId") String customerUserId,
                                                            @RequestParam("current") Long current,
                                                            @RequestParam("size") Long size
    );
    
    /**
     * 查询用户选中购物车商品
     */
    @GetMapping("/api/cart/product/{customerUserId}")
    Result<List<CartItemQuerySelectRespDTO>> querySelectCartByCustomerUserId(@PathVariable("customerUserId") String customerUserId);
}
