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

package org.opengoofy.congomall.biz.basicdata.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.basicdata.application.resp.RegionInfoRespDTO;
import org.opengoofy.congomall.biz.basicdata.application.service.RegionInfoService;
import org.opengoofy.congomall.biz.basicdata.domain.aggregate.RegionInfo;
import org.opengoofy.congomall.biz.basicdata.domain.repository.RegionInfoRepository;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行政区划接口层实现
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@RequiredArgsConstructor
public class RegionInfoServiceImpl implements RegionInfoService {
    
    private final RegionInfoRepository regionInfoRepository;
    
    @Override
    public List<RegionInfoRespDTO> listAllRegionInfo() {
        List<RegionInfo> regionInfoList = regionInfoRepository.listAllRegionInfo();
        return BeanUtil.convert(regionInfoList, RegionInfoRespDTO.class);
    }
    
    @Override
    public List<RegionInfoRespDTO> listRegionInfoByLevel(Integer level) {
        List<RegionInfo> regionInfoList = regionInfoRepository.listRegionInfoByLevel(level);
        return BeanUtil.convert(regionInfoList, RegionInfoRespDTO.class);
    }
    
    @Override
    public List<RegionInfoRespDTO> listRegionInfoByCode(String code) {
        List<RegionInfo> regionInfoList = regionInfoRepository.listRegionInfoByCode(code);
        return BeanUtil.convert(regionInfoList, RegionInfoRespDTO.class);
    }
    
    @Override
    public List<RegionInfoRespDTO> listRegionInfoByParent(String parent) {
        List<RegionInfo> regionInfoList = regionInfoRepository.listRegionInfoByParent(parent);
        return BeanUtil.convert(regionInfoList, RegionInfoRespDTO.class);
    }
}
