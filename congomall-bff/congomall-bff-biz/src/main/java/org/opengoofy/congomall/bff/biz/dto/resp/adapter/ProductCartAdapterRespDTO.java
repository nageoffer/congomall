package org.opengoofy.congomall.bff.biz.dto.resp.adapter;

import lombok.Data;

/**
 * 购物车适配返回对象
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class ProductCartAdapterRespDTO {
    
    private String checked;
    
    private Integer limitNum;
    
    private Long productId;
    
    private String productImg;
    
    private String productName;
    
    private Integer productNum;
    
    private Integer salePrice;
}
