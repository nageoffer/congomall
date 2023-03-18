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

package org.opengoofy.congomall.biz.message.infrastructure.handler;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.message.domain.acl.MailMessageProduce;
import org.opengoofy.congomall.biz.message.domain.entity.MessageSend;
import org.opengoofy.congomall.biz.message.infrastructure.dao.entity.MailTemplateDO;
import org.opengoofy.congomall.biz.message.infrastructure.dao.mapper.MailTemplateMapper;
import org.opengoofy.congomall.springboot.starter.base.Singleton;
import org.opengoofy.congomall.springboot.starter.base.init.ApplicationInitializingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

/**
 * 邮箱消息发送
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@AllArgsConstructor
public class MailMessageProduceImpl implements ApplicationListener<ApplicationInitializingEvent>, MailMessageProduce {
    
    private final MailTemplateMapper mailTemplateMapper;
    
    private final JavaMailSender javaMailSender;
    
    private final Configuration configuration;
    
    @SneakyThrows
    @Override
    public boolean send(MessageSend messageSend) {
        try {
            MailTemplateDO mailTemplateDO = mailTemplateMapper.selectOne(Wrappers.lambdaQuery(MailTemplateDO.class).eq(MailTemplateDO::getTemplateId, messageSend.getTemplateId()));
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
            String[] templateParams = mailTemplateDO.getTemplateParam().split(",");
            if (ArrayUtil.isNotEmpty(templateParams)) {
                for (int i = 0; i < templateParams.length; i++) {
                    model.put(templateParams[i], messageSend.getParamList().get(i));
                }
            }
            String templateKey = messageSend.getTemplateId() + ".ftl";
            Template template = Singleton.get(templateKey, () -> {
                try {
                    return configuration.getTemplate(templateKey);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(html, true);
            javaMailSender.send(mimeMessage);
        } catch (Throwable ex) {
            log.error("邮件发送失败，Request: {}", JSONUtil.toJsonStr(messageSend), ex);
            return false;
        }
        return true;
    }
    
    /**
     * 初始化邮箱模板
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationInitializingEvent event) {
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/*.ftl");
        for (Resource resource : resources) {
            String templateName = resource.getFilename();
            Singleton.put(templateName, configuration.getTemplate(templateName));
        }
    }
}
