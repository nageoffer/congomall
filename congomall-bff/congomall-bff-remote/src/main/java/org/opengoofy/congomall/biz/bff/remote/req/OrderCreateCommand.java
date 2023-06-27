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

package org.opengoofy.congomall.biz.bff.remote.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单创建
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class OrderCreateCommand {
    
    @ApiModelProperty("用户id")
    private String customerUserId;
    
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;
    
    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;
    
    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;
    
    @ApiModelProperty("订单来源")
    private Integer source;
    
    @ApiModelProperty("订单类型 0：正常订单 1：秒杀订单 2：促销订单")
    private Integer type;
    
    @ApiModelProperty("收货人")
    private String cneeName;
    
    @ApiModelProperty("收货人电话")
    private String cneePhone;
    
    @ApiModelProperty("收货人邮编")
    private String cneePostCode;
    
    @ApiModelProperty("收货人所在省")
    private String cneeProvinc;
    
    @ApiModelProperty("收货人所在市")
    private String cneeCity;
    
    @ApiModelProperty("收货人所在区")
    private String cneeRegion;
    
    @ApiModelProperty("收货人详细地址")
    private String cneeDetailAddress;
    
    @ApiModelProperty("订单备注信息")
    private String remark;
}
