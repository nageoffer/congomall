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

package org.opengoofy.congomall.biz.pay.domain.base;

import lombok.Data;

/**
 * 支付宝支付请求入参
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Data
public final class AliPayRequest extends AbstractPayRequest {
    
    /**
     * 商户订单号
     * 由商家自定义，64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复
     */
    private String outTradeNo;
    
    /**
     * 订单总金额
     * 单位为元，精确到小数点后两位，取值范围：[0.01,100000000]
     */
    private String totalAmount;
    
    /**
     * 订单标题
     * 注意：不可使用特殊字符，如 /，=，& 等
     */
    private String subject;
    
    /**
     * 产品码
     * 商家和支付宝签约的产品码。 枚举值（点击查看签约情况）：
     * <a target="_blank" href="https://opensupport.alipay.com/support/codelab/detail/766/772">FAST_INSTANT_TRADE_PAY</a>：新快捷即时到账产品
     * 注：目前仅支持FAST_INSTANT_TRADE_PAY
     */
    private String productCode;
    
    @Override
    public AliPayRequest getAliPayRequest() {
        return this;
    }
}
