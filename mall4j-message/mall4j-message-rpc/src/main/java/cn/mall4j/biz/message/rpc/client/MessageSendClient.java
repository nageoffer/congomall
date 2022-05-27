package cn.mall4j.biz.message.rpc.client;

import cn.mall4j.biz.message.rpc.req.MailSendCommand;
import cn.mall4j.biz.message.rpc.resp.MessageSendRespDTO;
import cn.mall4j.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 消息发送 eg：邮箱
 */
@FeignClient("mall4j-message")
public interface MessageSendClient {
    
    /**
     * 邮箱消息发送
     */
    @PostMapping("/mall4j-message/v1/message/mail/send")
    Result<MessageSendRespDTO> mailMessageSend(@RequestBody @Valid MailSendCommand mailSendCommand);
}
