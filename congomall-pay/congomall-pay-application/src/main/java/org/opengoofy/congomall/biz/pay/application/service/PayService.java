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

package org.opengoofy.congomall.biz.pay.application.service;

import org.opengoofy.congomall.biz.pay.application.req.PayCommand;
import org.opengoofy.congomall.biz.pay.application.resp.PayRespDTO;

/**
 * 支付接口
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public interface PayService {
    
    /**
     * 公共支付，对接支付宝、微信等常用支付方式
     *
     * @param requestParam 支付请求入参
     * @return 支付返回结果
     */
    PayRespDTO commonPay(PayCommand requestParam);
}
