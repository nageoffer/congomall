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

package cn.mall4j.biz.customer.user.infrastructure.mq.consumer;

import cn.mall4j.biz.customer.user.domain.entity.CustomerUser;
import cn.mall4j.biz.customer.user.domain.event.CustomerOperationLogEvent;
import cn.mall4j.biz.customer.user.domain.repository.CustomerUserRepository;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 端用户操作日志消息消费者
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomerUserOperationLogConsume {
    
    private final CustomerUserRepository customerUserRepository;
    
    @StreamListener(Sink.INPUT)
    public void customerUserOperationLog(@Payload CustomerOperationLogEvent customerOperationLogEvent, @Headers Map headers) {
        long startTime = System.currentTimeMillis();
        try {
            CustomerUser customerUser = CustomerUser.builder()
                    .customerUserId(customerOperationLogEvent.getAfterCustomerUser().getId())
                    .customerOperationLogEvent(customerOperationLogEvent)
                    .build();
            customerUserRepository.saveOperationLog(customerUser);
        } finally {
            log.info("Keys: {}, Msg id: {}, Execute time: {} ms, Message: {}", headers.get("rocketmq_KEYS"), headers.get("rocketmq_MESSAGE_ID"), System.currentTimeMillis() - startTime,
                    JSON.toJSONString(customerOperationLogEvent));
        }
    }
}
