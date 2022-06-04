package cn.mall4j.biz.customer.user.application.service.handler.login;

import cn.mall4j.biz.customer.user.application.req.UserLoginCommand;
import cn.mall4j.biz.customer.user.application.resp.UserLoginRespDTO;
import cn.mall4j.biz.customer.user.domain.entity.CustomerUser;
import cn.mall4j.biz.customer.user.domain.repository.CustomerUserRepository;
import cn.mall4j.springboot.starter.cache.DistributedCache;
import cn.mall4j.springboot.starter.cache.toolkit.CacheUtil;
import cn.mall4j.springboot.starter.design.pattern.strategy.AbstractExecuteStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static cn.mall4j.biz.customer.user.common.CacheConstant.LOGIN_USER_VERIFY_CODE;

/**
 * 邮箱登录
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Component
@AllArgsConstructor
public class MailLoginCommandHandler implements AbstractExecuteStrategy<UserLoginCommand, UserLoginRespDTO> {
    
    private final DistributedCache distributedCache;
    
    private final CustomerUserRepository customerUserRepository;
    
    @Override
    public String mark() {
        return "customer_user_login_mail";
    }
    
    @Override
    public UserLoginRespDTO executeResp(UserLoginCommand requestParam) {
        CustomerUser customerUser = CustomerUser.builder().verifyCode(requestParam.getMailValidCode()).build();
        // 获取缓存中的验证码
        String verifyCode = distributedCache.get(CacheUtil.buildKey(LOGIN_USER_VERIFY_CODE, requestParam.getMail()), String.class);
        // 检查验证码正确性
        customerUser.checkoutValidCode(verifyCode);
        CustomerUser actual = customerUserRepository.findByMail(requestParam.getMail());
        String accessToken = actual.generateAccessToken();
        return new UserLoginRespDTO(actual.getCustomerUserId(), actual.getUsername(), actual.getAccountNumber(), accessToken);
    }
}
