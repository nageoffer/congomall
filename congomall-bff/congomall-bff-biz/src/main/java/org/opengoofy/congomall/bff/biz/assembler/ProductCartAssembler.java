package org.opengoofy.congomall.bff.biz.assembler;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.opengoofy.congomall.bff.biz.dto.resp.adapter.ProductCartAdapterRespDTO;
import org.opengoofy.congomall.bff.remote.resp.CartItemRespDTO;

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
