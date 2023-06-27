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

package org.opengoofy.congomall.biz.bff.service.impl;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.bff.assembler.NavigationAssembler;
import org.opengoofy.congomall.biz.bff.dao.entity.NavigationDO;
import org.opengoofy.congomall.biz.bff.dao.mapper.NavigationMapper;
import org.opengoofy.congomall.biz.bff.dto.req.NavigationReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.NavigationRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.NavigationAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.NavigationService;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 导航栏接口实现
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Service
@RequiredArgsConstructor
public class NavigationServiceImpl implements NavigationService {
    
    private final NavigationMapper navigationMapper;
    private final NavigationAssembler navigationAssembler;
    
    @Override
    @Cached(name = "navigation:", key = "'list-all-navigation'", expire = 24, timeUnit = TimeUnit.HOURS)
    public List<NavigationAdapterRespDTO> listAllNavigation() {
        List<NavigationDO> navigationDOList = navigationMapper.selectList(Wrappers.emptyWrapper());
        List<NavigationRespDTO> navigationRespDTOList = BeanUtil.convert(navigationDOList, NavigationRespDTO.class);
        return navigationAssembler.convert(navigationRespDTOList);
    }
    
    @Override
    public void saveNavigation(NavigationReqDTO requestParam) {
        navigationMapper.insert(BeanUtil.convert(requestParam, NavigationDO.class));
    }
    
    @Override
    public void updateNavigation(NavigationReqDTO requestParam) {
        navigationMapper.updateById(BeanUtil.convert(requestParam, NavigationDO.class));
    }
    
    @Override
    public void deleteNavigation(Integer id) {
        navigationMapper.deleteById(id);
    }
}
