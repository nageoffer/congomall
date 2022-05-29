package cn.mall4j.biz.customer.user.application.req;

import lombok.Data;

/**
 * 用户验证码
 */
@Data
public class UserVerifyCodeCommand {
    
    /**
     * 验证类型
     */
    private String type;
    
    /**
     * 接收者
     */
    private String receiver;
}
