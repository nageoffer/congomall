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

package org.opengoofy.congomall.biz.message.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.biz.message.application.req.MessageSendQuery;
import org.opengoofy.congomall.biz.message.application.resp.MessageSendQueryRespDTO;
import org.opengoofy.congomall.biz.message.application.service.MessageSendQueryService;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 消息发送查询控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@AllArgsConstructor
@Api(tags = "消息发送查询")
public class MessageSendQueryController {
    
    private final MessageSendQueryService messageSendQueryService;
    
    @PostMapping("/api/message/result")
    @ApiOperation(value = "查询消息发送结果", notes = "根据消息发送时间和接收人集合查询发送结果")
    public Result<List<MessageSendQueryRespDTO>> listMessageSendResult(@RequestBody MessageSendQuery requestParam) {
        List<MessageSendQueryRespDTO> result = messageSendQueryService.listMessageSendResult(requestParam);
        return Results.success(result);
    }
    
    @GetMapping("/api/message/result/{messageSendId}")
    @ApiOperation(value = "查询消息发送结果", notes = "根据消息发送ID查询发送结果")
    public Result<MessageSendQueryRespDTO> getMessageSendResultByMsgId(@PathVariable("messageSendId") String messageSendId) {
        MessageSendQueryRespDTO result = messageSendQueryService.getMessageSendResultByMsgId(messageSendId);
        return Results.success(result);
    }
}
