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

package org.opengoofy.congomall.biz.order.domain.common;

import lombok.AllArgsConstructor;
import org.opengoofy.congomall.springboot.starter.convention.errorcode.IErrorCode;

/**
 * 订单创建错误码枚举
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@AllArgsConstructor
public enum OrderCreateErrorCodeEnum implements IErrorCode {
    
    CUSTOMER_USER_ID_NOTNULL("A006001", "用户ID不能为空"),
    
    TOTAL_AMOUNT_NOTNULL("A006002", "订单总金额不能为空"),
    
    PAY_AMOUNT_NOTNULL("A006003", "支付金额不能为空"),
    
    FREIGHT_AMOUNT_NOTNULL("A006004", "运费金额不能为空"),
    
    SOURCE_NOTNULL("A006005", "订单来源不能为空"),
    
    // xxx 这里应该把所有订单入参创建对应错误码，因为重复工作量所以暂时验证上述这些
    
    TOTAL_AMOUNT_ERROR("A006051", "订单金额错误，最低不能小于或等于0"),
    
    PAY_AMOUNT_ERROR("A006052", "支付金额错误，最低不能小于或等于0"),
    
    FREIGHT_AMOUNT_ERROR("A006053", "运费金额错误，最低不能小于0"),
    
    AMOUNT_VERIFICATION_ERROR("A006054", "订单金额验证失败"),
    
    PRODUCT_CART_ISNULL_ERROR("A006100", "获取购物车商品信息失败"),
    
    PRODUCT_STOCK_VERIFY_ERROR("A006101", "商品库存验证失败");
    
    /**
     * 错误码
     */
    private final String code;
    
    /**
     * 错误提示消息
     */
    private final String message;
    
    @Override
    public String code() {
        return code;
    }
    
    @Override
    public String message() {
        return message;
    }
}
