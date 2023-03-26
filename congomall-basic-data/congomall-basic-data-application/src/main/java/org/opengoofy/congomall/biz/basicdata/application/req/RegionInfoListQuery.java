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

package org.opengoofy.congomall.biz.basicdata.application.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 行政区划查询
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class RegionInfoListQuery {
    
    @ApiModelProperty("行政区划编号")
    private String code;
    
    @ApiModelProperty("行政区划名称")
    private String name;
    
    @ApiModelProperty("上级行政区划")
    private String parent;
    
    @ApiModelProperty("层级")
    private Integer level;
}
