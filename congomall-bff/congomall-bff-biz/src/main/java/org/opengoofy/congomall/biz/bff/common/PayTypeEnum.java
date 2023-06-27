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

package org.opengoofy.congomall.biz.bff.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * 支付方式枚举
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RequiredArgsConstructor
public enum PayTypeEnum {
    
    /**
     * 阿里支付宝
     */
    ALI_PAY(0, "AliPay");
    
    @Getter
    private final int code;
    
    @Getter
    private final String name;
    
    public static String getNameByCode(int code) {
        return Arrays.stream(PayTypeEnum.values())
                .filter(each -> Objects.equals(code, each.getCode()))
                .findFirst()
                .map(PayTypeEnum::getName)
                .orElse(null);
    }
}
