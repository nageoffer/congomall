package cn.mall4j.biz.customer.user.application.service.handler;

import cn.hutool.core.util.RandomUtil;
import cn.mall4j.biz.customer.user.application.req.UserVerifyCodeCommand;
import cn.mall4j.biz.customer.user.infrastructure.remote.MessageSendRemoteService;
import cn.mall4j.biz.customer.user.infrastructure.remote.dto.MailSendRemoteCommand;
import cn.mall4j.ddd.framework.core.domain.CommandHandler;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * C 端用户验证码
 */
@Component
@RequiredArgsConstructor
public class CustomerUserVerifyCodeCommandHandler implements CommandHandler<UserVerifyCodeCommand, Boolean> {
    
    private final MessageSendRemoteService messageSendRemoteService;
    
    @Value("${customer.user.register.verify.sender}")
    private String sender;
    
    @Value("${customer.user.register.verify.template-id}")
    private String templateId;
    
    @Override
    public Boolean handler(UserVerifyCodeCommand requestParam) {
        String verifyCode = RandomUtil.randomNumbers(6);
        MailSendRemoteCommand remoteCommand = new MailSendRemoteCommand();
        remoteCommand.setTitle("Mall4J邮箱验证码提醒")
                .setReceiver(requestParam.getReceiver())
                .setSender(sender)
                .setTemplateId(templateId)
                .setParamList(Lists.newArrayList(verifyCode));
        messageSendRemoteService.mailMessageSend(remoteCommand);
        return Boolean.TRUE;
    }
}
