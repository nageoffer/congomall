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

package org.opengoofy.congomall.biz.message.application.service;

import org.opengoofy.congomall.biz.message.application.req.MessageSendQuery;
import org.opengoofy.congomall.biz.message.application.resp.MessageSendQueryRespDTO;

import java.util.List;

/**
 * 消息发送查询
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface MessageSendQueryService {
    
    /**
     * 根据消息发送时间和接收人集合查询发送结果
     *
     * @param requestParam 消息发送结果查询入参
     * @return 消息发送查询返回结果
     */
    List<MessageSendQueryRespDTO> listMessageSendResult(MessageSendQuery requestParam);
    
    /**
     * 根据消息发送 ID 查询发送结果
     *
     * @param messageSendId 消息发送 ID
     * @return 消息发送查询返回结果
     */
    MessageSendQueryRespDTO getMessageSendResultByMsgId(String messageSendId);
}
