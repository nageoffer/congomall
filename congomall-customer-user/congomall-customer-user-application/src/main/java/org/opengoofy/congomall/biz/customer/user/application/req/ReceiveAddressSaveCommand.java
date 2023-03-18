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

package org.opengoofy.congomall.biz.customer.user.application.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 新增收货地址
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@ApiModel("新增收货地址")
public class ReceiveAddressSaveCommand {
    
    /**
     * c端用户id
     */
    private String customerUserId;
    
    /**
     * 收货人名称
     */
    private String name;
    
    /**
     * 收货人电话
     */
    private String phone;
    
    /**
     * 是否默认 0：否 1：是
     */
    private Integer defaultFlag;
    
    /**
     * 标签 0：家 1：公司
     */
    private Integer tag;
    
    /**
     * 邮政编码
     */
    private String postCode;
    
    /**
     * 省
     */
    private String province;
    
    /**
     * 市
     */
    private String city;
    
    /**
     * 区
     */
    private String region;
    
    /**
     * 详细地址
     */
    private String detailAddress;
}
