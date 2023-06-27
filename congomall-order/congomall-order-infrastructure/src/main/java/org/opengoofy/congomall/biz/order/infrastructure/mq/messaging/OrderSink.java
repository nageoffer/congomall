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

package org.opengoofy.congomall.biz.order.infrastructure.mq.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 订单 Sink
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface OrderSink {
    
    String DELAY_CLOSE_ORDER = "delayCloseOrder";
    
    /**
     * 延迟关闭订单
     */
    @Input(OrderSink.DELAY_CLOSE_ORDER)
    SubscribableChannel delayCloseOrder();
    
    String PAY_RESULT_NOTIFY = "payResultNotify";
    
    /**
     * 支付结果通知
     */
    @Input(OrderSink.PAY_RESULT_NOTIFY)
    SubscribableChannel payResultNotify();
}
