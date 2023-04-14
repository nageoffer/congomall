package org.opengoofy.congomall.bff.biz.dto.resp.adapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 捐赠适配返回对象
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class DonationAdapterRespDTO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 支付方式 0：支付宝
     */
    private String payType;
    
    /**
     * 捐赠金额
     */
    private Integer money;
    
    /**
     * 留言
     */
    private String info;
    
    /**
     * 捐赠时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}
