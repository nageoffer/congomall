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

package org.opengoofy.congomall.biz.message.infrastructure.facade;

import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.message.domain.acl.MailMessageProduce;
import org.opengoofy.congomall.biz.message.domain.entity.MessageSend;
import org.opengoofy.congomall.biz.message.domain.repository.MessageSendRepository;
import org.springframework.stereotype.Component;

/**
 * 邮件消息发送外观
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
@RequiredArgsConstructor
public final class MessageSendFacade {
    
    private final MessageSendRepository messageSendRepository;
    
    private final MailMessageProduce mailMessageProduce;
    
    /**
     * 邮箱消息发送
     *
     * @param messageSend 消息发送实体
     */
    public void mailMessageSend(MessageSend messageSend) {
        boolean sendResult = mailMessageProduce.send(messageSend);
        messageSend.setSendResult(sendResult);
        messageSendRepository.mailMessageSave(messageSend);
    }
}
