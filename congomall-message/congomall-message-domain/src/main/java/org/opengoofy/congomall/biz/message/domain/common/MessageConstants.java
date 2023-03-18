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

package org.opengoofy.congomall.biz.message.domain.common;

import cn.hutool.core.collection.ListUtil;

import java.util.List;

/**
 * 消息常量类
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class MessageConstants {
    
    /**
     * 短信发送渠道集合
     */
    public static final List<Integer> SMS_MESSAGE_CHANNELS = ListUtil.of(
            MessageTypeEnum.SMS_VERIFICATION_MESSAGE.getType(),
            MessageTypeEnum.SMS_INFORM_MESSAGE.getType(),
            MessageTypeEnum.SMS_MARKETING_MESSAGE.getType());
    
    /**
     * 短信消息模板
     */
    public static final String SMS_MESSAGE_KEY = "SMS_MESSAGE_";
}
