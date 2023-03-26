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

package org.opengoofy.congomall.biz.basicdata.interfaces.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.basicdata.application.resp.RegionInfoRespDTO;
import org.opengoofy.congomall.biz.basicdata.application.service.RegionInfoService;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.opengoofy.congomall.springboot.starter.log.annotation.MLog;
import org.opengoofy.congomall.springboot.starter.web.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 行政区划控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@Api(tags = "行政区划")
@RequiredArgsConstructor
public class RegionInfoController {
    
    private final RegionInfoService regionInfoService;
    
    @MLog(output = false)
    @GetMapping("/api/basics-data/region/all")
    @ApiOperation(value = "查询行政区划数据", notes = "查询所有行政区划数据")
    public Result<List<RegionInfoRespDTO>> listAllRegionInfo() {
        return Results.success(regionInfoService.listAllRegionInfo());
    }
    
    @MLog(output = false)
    @GetMapping("/api/basics-data/region/list/level/{level}")
    @ApiOperation(value = "查询行政区划数据", notes = "根据层级查询行政区划数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level", value = "层级", example = "1")
    })
    public Result<List<RegionInfoRespDTO>> listRegionInfoByLevel(@PathVariable("level") Integer level) {
        return Results.success(regionInfoService.listRegionInfoByLevel(level));
    }
    
    @MLog(output = false)
    @GetMapping("/api/basics-data/region/list/code/{code}")
    @ApiOperation(value = "查询行政区划数据", notes = "根据编号查询行政区划数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "行政区划编号", example = "110000")
    })
    public Result<List<RegionInfoRespDTO>> listRegionInfoByCode(@PathVariable("code") String code) {
        return Results.success(regionInfoService.listRegionInfoByCode(code));
    }
    
    @MLog(output = false)
    @GetMapping("/api/basics-data/region/list/parent/{parent}")
    @ApiOperation(value = "查询行政区划数据", notes = "根据上级行政区划编码查询行政区划数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parent", value = "上级行政区划编码", example = "CN0001")
    })
    public Result<List<RegionInfoRespDTO>> listRegionInfoByParent(@PathVariable("parent") String parent) {
        return Results.success(regionInfoService.listRegionInfoByParent(parent));
    }
}
