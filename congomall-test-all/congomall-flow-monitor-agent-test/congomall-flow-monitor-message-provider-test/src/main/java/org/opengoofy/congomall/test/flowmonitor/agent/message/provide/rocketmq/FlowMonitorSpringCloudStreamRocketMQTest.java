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

package org.opengoofy.congomall.test.flowmonitor.agent.message.provide.rocketmq;

import com.alibaba.nacos.common.utils.ThreadUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 流量治理之 RocketMQ 测试
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "RocketMQ测试")
public class FlowMonitorSpringCloudStreamRocketMQTest {
    
    private final MessageChannel output;
    
    @GetMapping("/api/message-service/send-mq")
    public String sendMessageTest() {
        Random random = new Random();
        int nextInt = random.nextInt(50);
        ThreadUtils.sleep(nextInt);
        Message<?> message = MessageBuilder
                .withPayload("Flow Monitor SpringCloud Stream RocketMQ Test.")
                .build();
        output.send(message);
        log.info("================ Provide sleep time: {}", nextInt);
        return "success";
    }
    
    @StreamListener(Sink.INPUT)
    public void springCloudStreamRocketMQConsumerTest(String param) {
        Random random = new Random();
        int nextInt = random.nextInt(50);
        ThreadUtils.sleep(nextInt);
        log.info("================ Consumer sleep time: {}, param: {}", nextInt, param);
        /*
         * if (nextInt % 2 == 0) { throw new RuntimeException(); }
         */
    }
}
