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

package org.opengoofy.congomall.biz.order.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.opengoofy.congomall.ddd.framework.core.domain.ValueObject;

import java.util.Date;

/**
 * 收货人信息
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CneeInfo implements ValueObject {
    
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
}
