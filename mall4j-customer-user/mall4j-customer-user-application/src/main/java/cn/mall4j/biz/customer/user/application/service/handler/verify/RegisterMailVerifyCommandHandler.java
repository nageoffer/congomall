package cn.mall4j.biz.customer.user.application.service.handler.verify;

import cn.mall4j.biz.customer.user.application.req.UserVerifyCodeCommand;
import cn.mall4j.biz.customer.user.common.CacheConstant;
import cn.mall4j.springboot.starter.design.pattern.strategy.AbstractExecuteStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户注册使用邮箱验证
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Component
@RequiredArgsConstructor
public class RegisterMailVerifyCommandHandler extends AbstractMailVerifySender implements AbstractExecuteStrategy<UserVerifyCodeCommand> {
    
    @Override
    public String mark() {
        return "customer_user_register_mail";
    }
    
    @Override
    public void execute(UserVerifyCodeCommand requestParam) {
        mailVerifySend(requestParam);
    }
    
    @Override
    protected String getCachePrefixKey() {
        return CacheConstant.REGISTER_USER_VERIFY_CODE;
    }
}
