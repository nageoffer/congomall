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

package org.opengoofy.congomall.biz.bff.dto.resp.adapter;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 首页板块适配请求对象
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class HomePanelAdapterRespDTO {
    
    /**
     * ID
     */
    private Integer id;
    
    /**
     * 限制数量
     */
    private Integer limitNum;
    
    /**
     * 板块名称
     */
    private String name;
    
    /**
     * 位置
     */
    private Integer position;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 排序
     */
    private Integer sortOrder;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
     * 类型
     */
    private Integer type;
    
    /**
     * 创建时间
     */
    private Date created;
    
    /**
     * 修改时间
     */
    private Date updated;
    
    /**
     * 商品数组
     */
    private List<HomePanelContentAdapterRespDTO> panelContents;
}
