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

package org.opengoofy.congomall.biz.pay.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.pay.application.resp.PayRespDTO;
import org.opengoofy.congomall.biz.pay.application.service.PayService;
import org.opengoofy.congomall.biz.pay.domain.aggregate.Pay;
import org.opengoofy.congomall.biz.pay.domain.base.PayCallbackRequest;
import org.opengoofy.congomall.biz.pay.domain.base.PayRequest;
import org.opengoofy.congomall.biz.pay.domain.base.PayResponse;
import org.opengoofy.congomall.biz.pay.domain.repository.PayRepository;
import org.opengoofy.congomall.biz.pay.infrastructure.handler.AliPayCallbackHandler;
import org.opengoofy.congomall.biz.pay.infrastructure.handler.AliPayNativeHandler;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.designpattern.strategy.AbstractStrategyChoose;
import org.springframework.stereotype.Service;

/**
 * 支付接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@RequiredArgsConstructor
public final class PayServiceImpl implements PayService {
    
    private final AbstractStrategyChoose abstractStrategyChoose;
    
    private final PayRepository payRepository;
    
    @Override
    public PayRespDTO commonPay(PayRequest requestParam) {
        /**
         * {@link AliPayNativeHandler}
         */
        // 策略模式：通过策略模式封装支付渠道和支付场景，用户支付时动态选择对应的支付组件
        PayResponse result = abstractStrategyChoose.chooseAndExecuteResp(requestParam.buildMark(), requestParam);
        payRepository.createPay(BeanUtil.convert(requestParam, Pay.class));
        return BeanUtil.convert(result, PayRespDTO.class);
    }
    
    @Override
    public void callback(PayCallbackRequest requestParam) {
        /**
         * {@link AliPayCallbackHandler}
         */
        // 策略模式：通过策略模式封装支付回调渠道，支付回调时动态选择对应的支付回调组件
        abstractStrategyChoose.chooseAndExecute(requestParam.buildMark(), requestParam);
    }
}
