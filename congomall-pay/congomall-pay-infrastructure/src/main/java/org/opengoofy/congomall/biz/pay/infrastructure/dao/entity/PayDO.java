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

package org.opengoofy.congomall.biz.pay.infrastructure.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.opengoofy.congomall.mybatisplus.springboot.starter.BaseDO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付持久层实体
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@TableName("pay_info")
public class PayDO extends BaseDO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 订单号
     */
    private String orderSn;
    
    /**
     * 商户订单号
     */
    private String outOrderSn;
    
    /**
     * 支付渠道
     */
    private String channel;
    
    /**
     * 支付环境
     */
    private String tradeType;
    
    /**
     * 订单标题
     */
    private String subject;
    
    /**
     * 交易凭证号
     */
    private String tradeNo;
    
    /**
     * 商户订单号
     * 由商家自定义，64个字符以内，仅支持字母、数字、下划线且需保证在商户端不重复
     */
    private String orderRequestId;
    
    /**
     * 交易总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 付款时间
     */
    private Date gmtPayment;
    
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 支付状态
     */
    private String status;
}
