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

package org.opengoofy.congomall.biz.message.infrastructure.repository;

import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.message.domain.entity.MessageSend;
import org.opengoofy.congomall.biz.message.domain.repository.MessageSendRepository;
import org.opengoofy.congomall.biz.message.infrastructure.converter.SendMessageConverter;
import org.opengoofy.congomall.biz.message.infrastructure.dao.entity.SendRecordDO;
import org.opengoofy.congomall.biz.message.infrastructure.dao.entity.SendRecordExtendDO;
import org.opengoofy.congomall.biz.message.infrastructure.dao.mapper.SendRecordExtendMapper;
import org.opengoofy.congomall.biz.message.infrastructure.dao.mapper.SendRecordMapper;
import org.opengoofy.congomall.springboot.starter.common.enums.StatusEnum;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/**
 * 消息发送仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Repository
@AllArgsConstructor
public class MessageSendRepositoryImpl implements MessageSendRepository {
    
    private final SendMessageConverter sendMessageConverter;
    private final SendRecordMapper sendRecordMapper;
    private final SendRecordExtendMapper sendRecordExtendMapper;
    
    @Override
    public void mailMessageSave(MessageSend messageSend) {
        SendRecordDO sendRecordDO = sendMessageConverter.sendMessageUserToDO(messageSend);
        sendRecordDO.setStatus(messageSend.getSendResult() ? StatusEnum.SUCCESS.code() : StatusEnum.FAIL.code());
        sendRecordDO.setSendTime(new Date());
        sendRecordMapper.insert(sendRecordDO);
        SendRecordExtendDO sendRecordExtendDO = SendRecordExtendDO.builder()
                .msgId(messageSend.getMessageSendId())
                .msgParam(Optional.ofNullable(messageSend.getParamList()).map(Object::toString).orElse(null))
                .build();
        sendRecordExtendMapper.insert(sendRecordExtendDO);
    }
}
