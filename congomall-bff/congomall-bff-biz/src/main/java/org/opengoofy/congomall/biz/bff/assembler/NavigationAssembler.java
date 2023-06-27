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

package org.opengoofy.congomall.biz.bff.assembler;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.opengoofy.congomall.biz.bff.dto.resp.NavigationRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.NavigationAdapterRespDTO;

import java.util.List;

/**
 * 导航栏返回对象映射转换
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Mapper(componentModel = "spring")
public interface NavigationAssembler {
    
    /**
     * 转换商城导航栏返回实体
     */
    @Mappings({
            @Mapping(source = "name", target = "picUrl"),
            @Mapping(source = "name", target = "productImageBig"),
            @Mapping(source = "url", target = "fullUrl"),
            @Mapping(source = "sort", target = "sortOrder"),
            @Mapping(source = "type", target = "type", defaultValue = "0"),
            @Mapping(source = "panelId", target = "panelId", defaultValue = "0")
    })
    NavigationAdapterRespDTO convert(NavigationRespDTO navigationRespDTO);
    
    /**
     * 批量转换商城导航栏返回实体
     */
    @InheritInverseConfiguration(name = "convert")
    List<NavigationAdapterRespDTO> convert(List<NavigationRespDTO> navigationRespDTOList);
}
