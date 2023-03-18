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

package org.opengoofy.congomall.biz.product.domain.dto;

import lombok.Data;

/**
 * 商品分类
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class ProductCategoryDTO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 分类名称
     */
    private String name;
    
    /**
     * 父级id
     */
    private Long parentId;
    
    /**
     * 层级
     */
    private Integer level;
    
    /**
     * 图标url
     */
    private String iconUrl;
    
    /**
     * 排序
     */
    private Integer sort;
    
    /**
     * 跳转地址
     */
    private String url;
    
    /**
     * 状态 0：展示 1：隐藏
     */
    private Integer status;
}
