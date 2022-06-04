package cn.mall4j.biz.customer.user.application.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录返回实体
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDTO {
    
    @ApiModelProperty(value = "用户ID")
    private Long customerUserId;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "账号")
    private String accountNumber;
    
    @ApiModelProperty(value = "Token")
    private String accessToken;
}
