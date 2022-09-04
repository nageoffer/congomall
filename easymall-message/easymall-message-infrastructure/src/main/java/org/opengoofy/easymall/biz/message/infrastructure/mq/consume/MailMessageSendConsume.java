/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.easymall.biz.message.infrastructure.mq.consume;

import cn.hutool.core.bean.BeanUtil;
import org.opengoofy.easymall.biz.message.domain.acl.MailMessageProduce;
import org.opengoofy.easymall.biz.message.domain.entity.MessageSend;
import org.opengoofy.easymall.biz.message.domain.event.MailMessageSendEvent;
import org.opengoofy.easymall.biz.message.domain.repository.MessageSendRepository;
import org.opengoofy.easymall.biz.message.infrastructure.mq.messaging.MessageSink;
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
 * @github https://github.com/agentart
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
            log.info("Keys: {}, Msg id: {}, Execute time: {} ms, Message: {}", headers.get("rocketmq_KEYS"), headers.get("rocketmq_MESSAGE_ID"), System.currentTimeMillis() - startTime,
                    JSON.toJSONString(mailMessageSendEvent));
        }
    }
}
