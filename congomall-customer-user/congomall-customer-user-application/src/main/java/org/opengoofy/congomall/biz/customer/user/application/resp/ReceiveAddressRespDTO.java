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

package org.opengoofy.congomall.biz.customer.user.application.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户收货地址出参
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 */
@Data
public class ReceiveAddressRespDTO {
    
    @ApiModelProperty("c端用户 id")
    private Long customerUserId;
    
    @ApiModelProperty("收货人名称")
    private String name;
    
    @ApiModelProperty("收货人电话")
    private String phone;
    
    @ApiModelProperty("是否默认 0：否 1：是")
    private int defaultFlag;
    
    @ApiModelProperty("标签 0：家 1：公司")
    private int tag;
    
    @ApiModelProperty("邮政编码")
    private String postCode;
    
    @ApiModelProperty("省")
    private String province;
    
    @ApiModelProperty("市")
    private String city;
    
    @ApiModelProperty("区")
    private String region;
    
    @ApiModelProperty("详细地址")
    private String detailAddress;
}
