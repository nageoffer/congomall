package org.opengoofy.congomall.bff.biz.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.opengoofy.congomall.mybatisplus.springboot.starter.BaseDO;

import java.util.Date;

/**
 * 捐赠实体
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@TableName("donation")
public class DonationDO extends BaseDO {
    
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 支付方式 0：支付宝
     */
    private Integer payType;
    
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
    private Date time;
}
