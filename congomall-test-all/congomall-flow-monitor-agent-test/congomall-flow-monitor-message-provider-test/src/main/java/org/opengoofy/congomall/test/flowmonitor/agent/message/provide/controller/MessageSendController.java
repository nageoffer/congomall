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

package org.opengoofy.congomall.test.flowmonitor.agent.message.provide.controller;

import com.alibaba.nacos.common.utils.ThreadUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * 消息发送控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@RestController
@Api(tags = "消息发送")
public class MessageSendController {
    
    @GetMapping("/api/message-service/info/{orderId}")
    public String getMessageInfoByOrderId(@PathVariable("orderId") String orderId) {
        Random random = new Random();
        int nextInt = random.nextInt(50);
        ThreadUtils.sleep(nextInt);
        if (nextInt % 2 == 0) {
            throw new RuntimeException();
        }
        log.info("================ sleep time: {}", nextInt);
        return orderId;
    }
    
    @GetMapping("/api/message-service/details")
    public String getMessageDetails(@RequestParam(value = "orderId", required = false) String orderId) {
        Random random = new Random();
        int nextInt = random.nextInt(50);
        ThreadUtils.sleep(nextInt);
        if (nextInt % 2 == 0) {
            throw new RuntimeException();
        }
        return orderId;
    }
}
