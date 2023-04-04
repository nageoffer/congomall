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

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.message.application.req.MessageSendQuery;
import org.opengoofy.congomall.biz.message.application.resp.MessageSendQueryRespDTO;
import org.opengoofy.congomall.biz.message.application.service.MessageSendQueryService;
import org.opengoofy.congomall.biz.message.domain.repository.MessageSendQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 消息发送查询
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@AllArgsConstructor
public class SendMessageQueryServiceImpl implements MessageSendQueryService {
    
    private final MessageSendQueryRepository messageSendQueryRepository;
    
    @Override
    public List<MessageSendQueryRespDTO> listMessageSendResult(MessageSendQuery requestParam) {
        org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery messageSendQuery = org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery.builder()
                .receiverList(requestParam.getReceiverList())
                .startTime(requestParam.getStartTime())
                .endTime(requestParam.getEndTime())
                .build();
        List<org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery> result = messageSendQueryRepository.listMessageSendResult(messageSendQuery);
        return org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil.convert(result, MessageSendQueryRespDTO.class);
    }
    
    @Override
    public MessageSendQueryRespDTO getMessageSendResultByMsgId(String messageSendId) {
        org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery messageSendQuery = org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery.builder()
                .messageSendIdList(Lists.newArrayList(Long.parseLong(messageSendId)))
                .build();
        List<org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery> result = messageSendQueryRepository.getMessageSendResultByMsgIds(messageSendQuery);
        return Optional.ofNullable(org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil.convert(result, MessageSendQueryRespDTO.class))
                .map(each -> each.get(0))
                .orElse(null);
    }
}
