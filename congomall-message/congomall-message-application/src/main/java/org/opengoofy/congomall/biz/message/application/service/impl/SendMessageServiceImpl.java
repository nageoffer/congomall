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

package org.opengoofy.congomall.biz.message.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.message.application.req.MailSendCommand;
import org.opengoofy.congomall.biz.message.application.resp.MessageSendRespDTO;
import org.opengoofy.congomall.biz.message.application.service.MessageSendService;
import org.opengoofy.congomall.biz.message.domain.common.MessageTypeEnum;
import org.opengoofy.congomall.biz.message.domain.entity.MessageSend;
import org.opengoofy.congomall.biz.message.domain.event.MailMessageSendEvent;
import org.opengoofy.congomall.biz.message.infrastructure.mq.produce.MessageSendProduce;
import org.opengoofy.congomall.springboot.starter.distributedid.SnowflakeIdUtil;
import org.springframework.stereotype.Service;

/**
 * 消息发送
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@AllArgsConstructor
public class SendMessageServiceImpl implements MessageSendService {
    
    private final MessageSendProduce messageSendProduce;
    
    @Override
    public MessageSendRespDTO mailMessageSend(MailSendCommand mailSendCommand) {
        String messageSendId = SnowflakeIdUtil.nextIdStr();
        MessageSend messageSend = MessageSend.builder()
                .title(mailSendCommand.getTitle())
                .sender(mailSendCommand.getSender())
                .receiver(mailSendCommand.getReceiver())
                .cc(mailSendCommand.getCc())
                .paramList(mailSendCommand.getParamList())
                .msgType(MessageTypeEnum.MAIL_MESSAGE.getType())
                .messageSendId(messageSendId)
                .templateId(mailSendCommand.getTemplateId())
                .build();
        messageSendProduce.mailMessageSend(BeanUtil.toBean(messageSend, MailMessageSendEvent.class));
        return new MessageSendRespDTO(messageSendId);
    }
}
