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

package org.opengoofy.congomall.biz.bff.web.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.bff.common.PageAdapter;
import org.opengoofy.congomall.biz.bff.common.ResultT;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.DonationAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.HomePanelAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.DonationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 捐赠服务控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "捐赠服务")
public class DonationController {
    
    private final DonationService donationService;
    
    @GetMapping("/member/thanks")
    @ApiOperation(value = "用户捐赠列表", notes = "用户捐赠列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "用户捐赠类表第几页", required = true, example = "1"),
            @ApiImplicitParam(name = "size", value = "用户捐赠类表每页多少条数据", required = true, example = "10")
    })
    public ResultT<PageAdapter<List<DonationAdapterRespDTO>>> pageQueryDonation(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return ResultT.success(donationService.pageQueryDonation(page, size));
    }
    
    @GetMapping("/goods/thank")
    @ApiOperation(value = "用户捐赠板块", notes = "用户捐赠板块")
    public ResultT<List<HomePanelAdapterRespDTO>> queryDonation() {
        return ResultT.success(Lists.newArrayList(donationService.queryDonation()));
    }
}
