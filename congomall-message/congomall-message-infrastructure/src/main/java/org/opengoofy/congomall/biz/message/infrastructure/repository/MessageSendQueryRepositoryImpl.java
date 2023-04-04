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

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.message.domain.entity.MessageSendQuery;
import org.opengoofy.congomall.biz.message.domain.repository.MessageSendQueryRepository;
import org.opengoofy.congomall.biz.message.infrastructure.dao.entity.SendRecordDO;
import org.opengoofy.congomall.biz.message.infrastructure.dao.mapper.SendRecordMapper;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 消息发送查询仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Repository
@AllArgsConstructor
public class MessageSendQueryRepositoryImpl implements MessageSendQueryRepository {
    
    private final SendRecordMapper sendRecordMapper;
    
    @Override
    public List<MessageSendQuery> listMessageSendResult(MessageSendQuery messageSendQuery) {
        LambdaQueryWrapper<SendRecordDO> queryWrapper = Wrappers.lambdaQuery(SendRecordDO.class)
                .between(
                        ObjectUtil.isAllNotEmpty(messageSendQuery.getStartTime(), messageSendQuery.getEndTime()),
                        SendRecordDO::getCreateTime, messageSendQuery.getStartTime(),
                        messageSendQuery.getEndTime())
                .in(CollUtil.isNotEmpty(messageSendQuery.getReceiverList()), SendRecordDO::getReceiver, messageSendQuery.getReceiverList());
        List<SendRecordDO> sendRecordDOList = sendRecordMapper.selectList(queryWrapper);
        return BeanUtil.convert(sendRecordDOList, MessageSendQuery.class);
    }
    
    @Override
    public List<MessageSendQuery> getMessageSendResultByMsgIds(MessageSendQuery messageSendQuery) {
        LambdaQueryWrapper<SendRecordDO> queryWrapper = Wrappers.lambdaQuery(SendRecordDO.class)
                .in(CollUtil.isNotEmpty(messageSendQuery.getMessageSendIdList()), SendRecordDO::getMessageSendId, messageSendQuery.getMessageSendIdList());
        List<SendRecordDO> sendRecordDOList = sendRecordMapper.selectList(queryWrapper);
        return BeanUtil.convert(sendRecordDOList, MessageSendQuery.class);
    }
}
