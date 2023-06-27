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

package org.opengoofy.congomall.biz.pay.infrastructure.mq.produce;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.opengoofy.congomall.biz.pay.domain.common.MessageRocketMQConstants;
import org.opengoofy.congomall.biz.pay.domain.event.PayResultNotifyMessageEvent;
import org.opengoofy.congomall.rocketmq.springboot.starter.core.MessageWrapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 支付消息通用发送生产者
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@AllArgsConstructor
public class PayMessageSendProduce {
    
    private final MessageChannel payOutput;
    
    /**
     * 支付消息通用发送
     *
     * @param event 支付结果消息发送事件
     */
    public void payResultNotifyMessageSend(PayResultNotifyMessageEvent event) {
        String keys = UUID.randomUUID().toString();
        Message<?> message = MessageBuilder
                .withPayload(new MessageWrapper(keys, event))
                .setHeader(MessageConst.PROPERTY_KEYS, keys)
                .setHeader(MessageConst.PROPERTY_TAGS, MessageRocketMQConstants.PAY_RESULT_MESSAGE_SEND_TAG)
                .build();
        long startTime = SystemClock.now();
        boolean sendResult = false;
        try {
            sendResult = payOutput.send(message, 2000L);
        } finally {
            log.info("支付结果通知消息发送，发送状态：{}，Keys：{}，执行时间：{} ms，消息内容：{}", sendResult, keys, SystemClock.now() - startTime, JSON.toJSONString(event));
        }
    }
}
