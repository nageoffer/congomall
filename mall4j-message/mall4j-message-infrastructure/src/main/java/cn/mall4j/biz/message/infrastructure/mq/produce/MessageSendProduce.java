package cn.mall4j.biz.message.infrastructure.mq.produce;

import cn.mall4j.biz.message.domain.common.MessageRocketMQConstants;
import cn.mall4j.biz.message.domain.event.MailMessageSendEvent;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 消息发送生产者
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Slf4j
@Component
@AllArgsConstructor
public class MessageSendProduce {
    
    private final MessageChannel output;
    
    /**
     * 邮箱消息发送
     *
     * @param mailMessageSendEvent
     */
    public void mailMessageSend(MailMessageSendEvent mailMessageSendEvent) {
        String keys = UUID.randomUUID().toString();
        Message<?> message = MessageBuilder
                .withPayload(JSON.toJSONString(mailMessageSendEvent))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, MessageRocketMQConstants.MESSAGE_MAIL_SEND_TAG)
                .build();
        long startTime = System.currentTimeMillis();
        boolean sendResult = false;
        try {
            sendResult = output.send(message, 2000L);
        } finally {
            log.info("邮箱消息发送，发送状态: {}, Keys: {}, 执行时间: {} ms, 消息内容: {}", sendResult, keys, System.currentTimeMillis() - startTime, JSON.toJSONString(mailMessageSendEvent));
        }
    }
}
