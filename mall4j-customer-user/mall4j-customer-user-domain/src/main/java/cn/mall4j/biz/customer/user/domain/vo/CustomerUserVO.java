package cn.mall4j.biz.customer.user.domain.vo;

import lombok.Data;

/**
 * C 端用户
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
public class CustomerUserVO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 昵称
     */
    private String name;
    
    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 邮箱
     */
    private String mail;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 性别
     */
    private Integer gender;
    
    /**
     * 头像
     */
    private String avatar;
}
