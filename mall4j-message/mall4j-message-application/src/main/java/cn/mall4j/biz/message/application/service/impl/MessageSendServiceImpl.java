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

package cn.mall4j.biz.message.application.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.mall4j.biz.message.application.service.MessageSendService;
import cn.mall4j.biz.message.domain.acl.MailMessageProduce;
import cn.mall4j.biz.message.domain.entity.MessageSend;
import cn.mall4j.biz.message.domain.repository.MessageSendRepository;
import cn.mall4j.biz.message.rpc.req.MailSendCommand;
import cn.mall4j.biz.message.rpc.resp.MessageSendRespDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 消息发送
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageSendServiceImpl implements MessageSendService {
    
    private final MessageSendRepository messageSendRepository;
    
    private final MailMessageProduce mailMessageProduce;
    
    @Override
    public MessageSendRespDTO mailMessageSend(MailSendCommand mailSendCommand) {
        String messageSendId = IdUtil.getSnowflakeNextIdStr();
        MessageSend messageSend = MessageSend.builder()
                .title(mailSendCommand.getTitle())
                .sender(mailSendCommand.getSender())
                .receiver(mailSendCommand.getReceiver())
                .cc(mailSendCommand.getCc())
                .paramList(mailSendCommand.getParamList())
                .messageSendId(messageSendId)
                .templateId(mailSendCommand.getTemplateId())
                .build();
        boolean sendResult = mailMessageProduce.send(messageSend);
        messageSend.setSendResult(sendResult);
        messageSendRepository.mailMessageSave(messageSend);
        return new MessageSendRespDTO(messageSendId);
    }
}
