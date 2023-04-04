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

package org.opengoofy.congomall.biz.basicdata.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.basicdata.domain.aggregate.RegionInfo;
import org.opengoofy.congomall.biz.basicdata.domain.repository.RegionInfoRepository;
import org.opengoofy.congomall.biz.basicdata.infrastructure.dao.mapper.RegionInfoMapper;
import org.opengoofy.congomall.biz.basicdata.infrastructure.dao.entity.RegionInfoDO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行政区划仓储层实现
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Repository
@RequiredArgsConstructor
public class RegionInfoRepositoryImpl implements RegionInfoRepository {
    
    private final RegionInfoMapper regionInfoMapper;
    
    @Override
    public List<RegionInfo> listAllRegionInfo() {
        LambdaQueryWrapper<RegionInfoDO> queryWrapper = Wrappers.lambdaQuery(RegionInfoDO.class).select(
                RegionInfoDO::getId,
                RegionInfoDO::getCode,
                RegionInfoDO::getName,
                RegionInfoDO::getParent,
                RegionInfoDO::getLevel,
                RegionInfoDO::getSort);
        List<RegionInfoDO> regionInfoDOList = regionInfoMapper.selectList(queryWrapper);
        return BeanUtil.convert(regionInfoDOList, RegionInfo.class);
    }
    
    @Override
    public List<RegionInfo> listRegionInfoByLevel(Integer level) {
        LambdaQueryWrapper<RegionInfoDO> queryWrapper = Wrappers.lambdaQuery(RegionInfoDO.class)
                .eq(RegionInfoDO::getLevel, level)
                .select(
                        RegionInfoDO::getId,
                        RegionInfoDO::getCode,
                        RegionInfoDO::getName,
                        RegionInfoDO::getParent,
                        RegionInfoDO::getLevel,
                        RegionInfoDO::getSort);
        List<RegionInfoDO> regionInfoDOList = regionInfoMapper.selectList(queryWrapper);
        return BeanUtil.convert(regionInfoDOList, RegionInfo.class);
    }
    
    @Override
    public List<RegionInfo> listRegionInfoByCode(String code) {
        LambdaQueryWrapper<RegionInfoDO> queryWrapper = Wrappers.lambdaQuery(RegionInfoDO.class)
                .eq(RegionInfoDO::getCode, code)
                .select(
                        RegionInfoDO::getId,
                        RegionInfoDO::getCode,
                        RegionInfoDO::getName,
                        RegionInfoDO::getParent,
                        RegionInfoDO::getLevel,
                        RegionInfoDO::getSort);
        List<RegionInfoDO> regionInfoDOList = regionInfoMapper.selectList(queryWrapper);
        return BeanUtil.convert(regionInfoDOList, RegionInfo.class);
    }
    
    @Override
    public List<RegionInfo> listRegionInfoByParent(String parent) {
        LambdaQueryWrapper<RegionInfoDO> queryWrapper = Wrappers.lambdaQuery(RegionInfoDO.class)
                .eq(RegionInfoDO::getParent, parent)
                .select(
                        RegionInfoDO::getId,
                        RegionInfoDO::getCode,
                        RegionInfoDO::getName,
                        RegionInfoDO::getParent,
                        RegionInfoDO::getLevel,
                        RegionInfoDO::getSort);
        List<RegionInfoDO> regionInfoDOList = regionInfoMapper.selectList(queryWrapper);
        return BeanUtil.convert(regionInfoDOList, RegionInfo.class);
    }
}
