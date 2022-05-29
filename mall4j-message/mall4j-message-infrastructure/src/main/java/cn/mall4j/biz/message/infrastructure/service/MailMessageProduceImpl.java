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

package cn.mall4j.biz.message.infrastructure.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.mall4j.biz.message.domain.acl.MailMessageProduce;
import cn.mall4j.biz.message.domain.entity.MessageSend;
import cn.mall4j.biz.message.infrastructure.dao.MailTemplateDO;
import cn.mall4j.biz.message.infrastructure.dao.MailTemplateMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

/**
 * 邮箱消息发送
 *
 * @author chen.ma
 * @github https://github.com/longtai-cn
 */
@Slf4j
@Component
@AllArgsConstructor
public class MailMessageProduceImpl implements MailMessageProduce {
    
    private final MailTemplateMapper mailTemplateMapper;
    
    private final JavaMailSender javaMailSender;
    
    private final Configuration configuration;
    
    @SneakyThrows
    @Override
    public boolean send(MessageSend messageSend) {
        try {
            List<MailTemplateDO> mailTemplateDOS = mailTemplateMapper.selectList(Wrappers.lambdaQuery(MailTemplateDO.class).eq(MailTemplateDO::getTemplateId, messageSend.getTemplateId()));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(messageSend.getSender());
            helper.setSubject(messageSend.getTitle());
            if (StrUtil.isNotBlank(messageSend.getCc())) {
                helper.setCc(messageSend.getCc().split(","));
            }
            if (StrUtil.isNotBlank(messageSend.getReceiver())) {
                helper.setTo(messageSend.getReceiver().split(","));
            }
            Map<String, Object> model = Maps.newHashMap();
            if (CollUtil.isNotEmpty(messageSend.getParamList())) {
                for (int i = 0; i < mailTemplateDOS.size(); i++) {
                    model.put(mailTemplateDOS.get(i).getTemplateParam(), messageSend.getParamList().get(i));
                }
            }
            Template template = configuration.getTemplate(messageSend.getTemplateId() + ".ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);
        } catch (Throwable ex) {
            log.error("邮件发送失败，Request: {}", JSONUtil.toJsonStr(messageSend), ex);
            return false;
        }
        return true;
    }
}
