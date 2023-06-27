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

package org.opengoofy.congomall.biz.bff.service;

import org.opengoofy.congomall.biz.bff.dto.req.NavigationReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.NavigationAdapterRespDTO;

import java.util.List;

/**
 * 导航栏接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface NavigationService {
    
    /**
     * 查询所有导航栏数据
     *
     * @return 全部导航栏数据
     */
    List<NavigationAdapterRespDTO> listAllNavigation();
    
    /**
     * 新增导航栏数据
     *
     * @param requestParam 新增入参
     */
    void saveNavigation(NavigationReqDTO requestParam);
    
    /**
     * 修改导航栏数据
     *
     * @param requestParam 修改入参
     */
    void updateNavigation(NavigationReqDTO requestParam);
    
    /**
     * 删除导航栏数据
     *
     * @param id 导航栏数据 ID
     */
    void deleteNavigation(Integer id);
}
