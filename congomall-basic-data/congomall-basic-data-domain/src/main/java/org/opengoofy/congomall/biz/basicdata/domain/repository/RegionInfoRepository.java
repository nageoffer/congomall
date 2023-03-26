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

package org.opengoofy.congomall.biz.basicdata.domain.repository;

import org.opengoofy.congomall.biz.basicdata.domain.aggregate.RegionInfo;

import java.util.List;

/**
 * 行政区划仓储层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface RegionInfoRepository {
    
    /**
     * 查询行政区划数据
     *
     * @return 全部行政区划数据
     */
    List<RegionInfo> listAllRegionInfo();
    
    /**
     * 根据层级查询行政区划数据
     *
     * @param level 层级，比如省市区三级，eg：1、2、3
     * @return 行政区划数据
     */
    List<RegionInfo> listRegionInfoByLevel(Integer level);
    
    /**
     * 根据编码查询行政区划数据
     *
     * @param code 行政区划编码，比如北京：110000
     * @return 行政区划数据
     */
    List<RegionInfo> listRegionInfoByCode(String code);
    
    /**
     * 根据上级行政区划编码查询行政区划数据
     *
     * @param parent 上级行政区划编码，比如北京：110000
     * @return 行政区划数据
     */
    List<RegionInfo> listRegionInfoByParent(String parent);
}
