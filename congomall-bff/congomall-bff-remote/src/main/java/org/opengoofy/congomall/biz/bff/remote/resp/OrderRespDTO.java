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

package org.opengoofy.congomall.biz.bff.remote.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单出参
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class OrderRespDTO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 用户id
     */
    private Long customerUserId;
    
    /**
     * 订单编号
     */
    private String orderSn;
    
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    
    /**
     * 支付金额
     */
    private BigDecimal payAmount;
    
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    
    /**
     * 支付方式
     */
    private Integer payType;
    
    /**
     * 支付时间
     */
    private Date payTime;
    
    /**
     * 订单来源
     */
    private Integer source;
    
    /**
     * 订单类型 0：正常订单 1：秒杀订单 2：促销订单
     */
    private Integer type;
    
    /**
     * 自动确认天数
     */
    private Integer autoConfirmDay;
    
    /**
     * 物流公司
     */
    private String deliveryCompany;
    
    /**
     * 物流单号
     */
    private String deliverySn;
    
    /**
     * 收货人
     */
    private String cneeName;
    
    /**
     * 收货人电话
     */
    private String cneePhone;
    
    /**
     * 收货人邮编
     */
    private String cneePostCode;
    
    /**
     * 收货人所在省
     */
    private String cneeProvinc;
    
    /**
     * 收货人所在市
     */
    private String cneeCity;
    
    /**
     * 收货人所在区
     */
    private String cneeRegion;
    
    /**
     * 收货人详细地址
     */
    private String cneeDetailAddress;
    
    /**
     * 收货时间
     */
    private Date receiveTime;
    
    /**
     * 订单备注信息
     */
    private String remark;
    
    /**
     * 收货状态 0：未接收 1：已接收
     */
    private Integer confirmFlag;
    
    /**
     * 订单创建时间
     */
    private Date createTime;
    
    /**
     * 发货时间
     */
    private Date deliveryTime;
    
    /**
     * 订单状态
     */
    private Integer status;
    
    /**
     * 商品集合
     */
    private List<OrderProductRespDTO> orderProducts;
}
