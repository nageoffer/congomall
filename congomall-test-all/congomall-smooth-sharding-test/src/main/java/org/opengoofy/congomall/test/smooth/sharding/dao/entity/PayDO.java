package org.opengoofy.congomall.test.smooth.sharding.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.opengoofy.congomall.mybatisplus.springboot.starter.BaseDO;

import java.util.Date;

/**
 * 支付 DO
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Data
@TableName("pay_info")
public class PayDO extends BaseDO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 支付单号
     */
    private String payNo;
    
    /**
     * 支付时间
     */
    private Date payTime;
}
