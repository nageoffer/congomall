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

package org.opengoofy.congomall.biz.message.infrastructure.mq.produce;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.opengoofy.congomall.biz.message.domain.common.MessageRocketMQConstants;
import org.opengoofy.congomall.biz.message.domain.event.MailMessageSendEvent;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 消息发送生产者
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@AllArgsConstructor
public class MessageSendProduce {
    
    private final MessageChannel messageOutput;
    
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
        long startTime = SystemClock.now();
        boolean sendResult = false;
        try {
            sendResult = messageOutput.send(message, 2000L);
        } finally {
            log.info("邮箱消息发送，发送状态: {}, Keys: {}, 执行时间: {} ms, 消息内容: {}", sendResult, keys, SystemClock.now() - startTime, JSON.toJSONString(mailMessageSendEvent));
        }
    }
}
