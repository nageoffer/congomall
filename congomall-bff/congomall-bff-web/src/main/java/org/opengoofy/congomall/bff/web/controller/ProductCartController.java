package org.opengoofy.congomall.bff.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.bff.biz.common.ResultT;
import org.opengoofy.congomall.bff.biz.dto.req.adapter.ProductCartAdapterReqDTO;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.bff.biz.service.ProductCartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 购物车控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "用户商品购物车")
public class ProductCartController {
    
    private final ProductCartService productCartService;
    
    @PostMapping("/member/cartList")
    @ApiOperation(value = "查询用户购物车", notes = "根据用户ID查询购物车数据")
    public ResultT<List<ProductCartAdapterRespDTO>> listAllProductCart(@RequestBody ProductCartAdapterReqDTO requestParam) {
        return ResultT.success(productCartService.listAllProductCart(requestParam.getUserId()));
    }
}
