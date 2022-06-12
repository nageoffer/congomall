package cn.mall4j.biz.customer.user.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@NoArgsConstructor
public class CustomerOperationLogVO {
    
    /**
     * 之前的
     */
    private CustomerUserVO beforeCustomerUser;
    
    /**
     * 操作后
     */
    private CustomerUserVO afterCustomerUser;
    
    public CustomerOperationLogVO(CustomerUserVO afterCustomerUser) {
        this.afterCustomerUser = afterCustomerUser;
    }
    
    public CustomerOperationLogVO(CustomerUserVO beforeCustomerUser, CustomerUserVO afterCustomerUser) {
        this.beforeCustomerUser = beforeCustomerUser;
        this.afterCustomerUser = afterCustomerUser;
    }
}
