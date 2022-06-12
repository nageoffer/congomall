package cn.mall4j.biz.customer.user.infrastructure.dao;

import cn.mall4j.mybatisplus.springboot.starter.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * C 端用户操作日志
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@NoArgsConstructor
@TableName("customer_user_operation_log")
public class CustomerOperationLogDO extends BaseDO {
    
    /**
     * 修改前
     */
    private String beforeContent;
    
    /**
     * 修改后
     */
    private String afterContent;
    
    /**
     * 修改内容
     */
    private String operationContent;
    
    public CustomerOperationLogDO(String afterContent) {
        this.afterContent = afterContent;
    }
    
    public CustomerOperationLogDO(String beforeContent, String afterContent, String operationContent) {
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.operationContent = operationContent;
    }
}
