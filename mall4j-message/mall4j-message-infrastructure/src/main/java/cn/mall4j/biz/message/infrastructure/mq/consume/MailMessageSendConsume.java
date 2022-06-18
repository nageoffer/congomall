package cn.mall4j.biz.message.infrastructure.mq.consume;

import cn.hutool.core.bean.BeanUtil;
import cn.mall4j.biz.message.domain.acl.MailMessageProduce;
import cn.mall4j.biz.message.domain.entity.MessageSend;
import cn.mall4j.biz.message.domain.event.MailMessageSendEvent;
import cn.mall4j.biz.message.domain.repository.MessageSendRepository;
import cn.mall4j.biz.message.infrastructure.mq.messaging.MessageSink;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 邮箱消息发送消费者
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Slf4j
@Component
@AllArgsConstructor
public class MailMessageSendConsume {
    
    private final MessageSendRepository messageSendRepository;
    
    private final MailMessageProduce mailMessageProduce;
    
    @StreamListener(MessageSink.MAIL_SEND)
    public void mailMessageSend(@Payload MailMessageSendEvent mailMessageSendEvent, @Headers Map headers) {
        long startTime = System.currentTimeMillis();
        try {
            MessageSend messageSend = BeanUtil.toBean(mailMessageSendEvent, MessageSend.class);
            boolean sendResult = mailMessageProduce.send(messageSend);
            messageSend.setSendResult(sendResult);
            messageSendRepository.mailMessageSave(messageSend);
        } finally {
            log.info("Keys: {}, Msg id: {}, Execute time: {} ms, Message: {}", headers.get("rocketmq_KEYS"), headers.get("rocketmq_MESSAGE_ID"), System.currentTimeMillis() - startTime, JSON.toJSONString(mailMessageSendEvent));
        }
    }
}
