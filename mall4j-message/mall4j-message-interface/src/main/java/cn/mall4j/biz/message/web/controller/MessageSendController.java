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

package cn.mall4j.biz.message.web.controller;

import cn.mall4j.biz.message.application.req.MailSendCommand;
import cn.mall4j.biz.message.application.resp.MessageSendRespDTO;
import cn.mall4j.biz.message.application.service.MessageSendService;
import cn.mall4j.springboot.starter.convention.result.Result;
import cn.mall4j.springboot.starter.log.annotation.MLog;
import cn.mall4j.springboot.starter.web.Results;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 消息发送控制器
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@MLog
@RestController
@AllArgsConstructor
@Api(tags = "消息发送")
@RequestMapping("/message/send")
public class MessageSendController {
    
    private final MessageSendService messageSendService;
    
    @PostMapping("/mail")
    @ApiOperation("邮箱消息发送")
    public Result<MessageSendRespDTO> sendMailMessage(@RequestBody @Valid MailSendCommand mailSendCommand) {
        MessageSendRespDTO result = messageSendService.mailMessageSend(mailSendCommand);
        return Results.success(result);
    }
}
