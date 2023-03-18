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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

import static org.opengoofy.congomall.biz.message.domain.common.MessageConstants.SMS_MESSAGE_KEY;

/**
 * 消息类型枚举
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RequiredArgsConstructor
public enum MessageTypeEnum {
    
    /**
     * 短信验证码消息
     */
    SMS_VERIFICATION_MESSAGE(0, SMS_MESSAGE_KEY),
    
    /**
     * 短信通知消息
     */
    SMS_INFORM_MESSAGE(1, SMS_MESSAGE_KEY),
    
    /**
     * 短信营销消息
     */
    SMS_MARKETING_MESSAGE(2, SMS_MESSAGE_KEY),
    
    /**
     * 微信模板消息
     */
    WE_CHART_MESSAGE(3, "WE_CHART_TEMPLATE_MESSAGE"),
    
    /**
     * 邮件消息
     */
    MAIL_MESSAGE(4, "MAIL_MESSAGE");
    
    @Getter
    private final Integer type;
    
    @Getter
    private final String platform;
    
    /**
     * 根据 type 获取 platform
     */
    public static String getPlatformByType(Integer type) {
        return Arrays.stream(MessageTypeEnum.values())
                .filter(each -> Objects.equals(type, each.getType())).findFirst()
                .map(MessageTypeEnum::getPlatform)
                .orElseThrow(RuntimeException::new);
    }
}
