package cn.mall4j.biz.customer.user.domain.event;

import cn.mall4j.biz.customer.user.domain.dto.CustomerUserDTO;
import cn.mall4j.ddd.framework.core.domain.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * C 端用户操作日志事件
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@NoArgsConstructor
public class CustomerOperationLogEvent implements DomainEvent {
    
    /**
     * 之前的
     */
    private CustomerUserDTO beforeCustomerUser;
    
    /**
     * 操作后
     */
    private CustomerUserDTO afterCustomerUser;
    
    public CustomerOperationLogEvent(CustomerUserDTO afterCustomerUser) {
        this.afterCustomerUser = afterCustomerUser;
    }
    
    public CustomerOperationLogEvent(CustomerUserDTO beforeCustomerUser, CustomerUserDTO afterCustomerUser) {
        this.beforeCustomerUser = beforeCustomerUser;
        this.afterCustomerUser = afterCustomerUser;
    }
}
