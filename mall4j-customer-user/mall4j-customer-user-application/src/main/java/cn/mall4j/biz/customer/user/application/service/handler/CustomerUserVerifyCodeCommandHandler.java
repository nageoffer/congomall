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

package cn.mall4j.biz.customer.user.application.service.handler;

import cn.hutool.core.util.RandomUtil;
import cn.mall4j.biz.customer.user.application.req.UserVerifyCodeCommand;
import cn.mall4j.biz.customer.user.infrastructure.remote.MessageSendRemoteService;
import cn.mall4j.biz.customer.user.infrastructure.remote.dto.MailSendRemoteCommand;
import cn.mall4j.ddd.framework.core.domain.CommandHandler;
import cn.mall4j.springboot.starter.cache.DistributedCache;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static cn.mall4j.biz.customer.user.common.CacheConstant.REGISTER_USER_VERIFY_CODE;

/**
 * C 端用户验证码
 */
@Component
@RequiredArgsConstructor
public class CustomerUserVerifyCodeCommandHandler implements CommandHandler<UserVerifyCodeCommand, Boolean> {
    
    private final MessageSendRemoteService messageSendRemoteService;
    
    private final DistributedCache distributedCache;
    
    @Value("${customer.user.register.verify.sender}")
    private String sender;
    
    @Value("${customer.user.register.verify.template-id}")
    private String templateId;
    
    @Override
    public Boolean handler(UserVerifyCodeCommand requestParam) {
        String verifyCode = RandomUtil.randomNumbers(6);
        distributedCache.put(REGISTER_USER_VERIFY_CODE, verifyCode);
        MailSendRemoteCommand remoteCommand = new MailSendRemoteCommand();
        remoteCommand.setTitle("Mall4J邮箱验证码提醒")
                .setReceiver(requestParam.getReceiver())
                .setSender(sender)
                .setTemplateId(templateId)
                .setParamList(Lists.newArrayList(verifyCode));
        messageSendRemoteService.mailMessageSend(remoteCommand);
        return Boolean.TRUE;
    }
}
