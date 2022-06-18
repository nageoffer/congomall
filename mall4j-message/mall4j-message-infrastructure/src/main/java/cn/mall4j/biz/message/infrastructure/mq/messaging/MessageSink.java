package cn.mall4j.biz.message.infrastructure.mq.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 消息 Sink
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public interface MessageSink {
    
    String MAIL_SEND = "mail-send";
    
    /**
     * 邮箱发送
     *
     * @return
     */
    @Input(MessageSink.MAIL_SEND)
    SubscribableChannel mailSend();
}
