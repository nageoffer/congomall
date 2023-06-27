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

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.bff.assembler.UserLoginAssembler;
import org.opengoofy.congomall.biz.bff.common.UserLoginSeataEnum;
import org.opengoofy.congomall.biz.bff.config.GeeTestProperties;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.UserLoginAdapterRepDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.GeeTestAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.UserLoginAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.service.UserLoginService;
import org.opengoofy.congomall.biz.bff.toolkit.GeeTestLib;
import org.opengoofy.congomall.biz.bff.remote.CustomerUserRemoteService;
import org.opengoofy.congomall.biz.bff.remote.req.UserLoginCommand;
import org.opengoofy.congomall.biz.bff.remote.resp.UserLoginRespDTO;
import org.opengoofy.congomall.springboot.starter.cache.DistributedCache;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户登录接口实现层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {
    
    private final CustomerUserRemoteService customerUserRemoteService;
    private final UserLoginAssembler userLoginAssembler;
    private final GeeTestProperties geeTestProperties;
    private final DistributedCache distributedCache;
    
    @Override
    public UserLoginAdapterRespDTO login(UserLoginAdapterRepDTO requestParam) {
        GeeTestLib geeTestLib = new GeeTestLib(geeTestProperties);
        int statusKey = Integer.parseInt(distributedCache.get(requestParam.getStatusKey(), String.class));
        String challenge = requestParam.getChallenge();
        String validate = requestParam.getValidate();
        String secCode = requestParam.getSeccode();
        int gtResult;
        if (statusKey == 1) {
            gtResult = geeTestLib.enhancedValidateRequest(challenge, validate, secCode, new HashMap<>());
        } else {
            gtResult = geeTestLib.failBackValidateRequest(challenge, validate, secCode);
        }
        UserLoginAdapterRespDTO actualResp = new UserLoginAdapterRespDTO();
        if (gtResult == 1) {
            UserLoginCommand userLoginCommand = userLoginAssembler.loginRequestConvert(requestParam);
            Result<UserLoginRespDTO> result = null;
            actualResp.setUsername(requestParam.getUserName());
            try {
                result = customerUserRemoteService.login(userLoginCommand);
            } catch (Throwable ex) {
                actualResp.setState(UserLoginSeataEnum.FAIL.getCode());
                log.error("调用用户服务登录失败", ex);
            }
            if (result != null && result.isSuccess()) {
                UserLoginRespDTO resultData = result.getData();
                actualResp.setToken(resultData.getAccessToken());
                actualResp.setId(String.valueOf(resultData.getCustomerUserId()));
                actualResp.setUsername(resultData.getAccountNumber());
                actualResp.setState(UserLoginSeataEnum.SUCCESS.getCode());
            }
        } else {
            actualResp.setState(UserLoginSeataEnum.FAIL.getCode());
        }
        return actualResp;
    }
    
    @Override
    public UserLoginAdapterRespDTO checkLogin(String token) {
        Result<UserLoginRespDTO> result = null;
        UserLoginAdapterRespDTO actualResp = new UserLoginAdapterRespDTO();
        try {
            result = customerUserRemoteService.checkLogin(token);
        } catch (Throwable ex) {
            log.error("调用用户服务检查登录状态失败", ex);
            actualResp.setState(UserLoginSeataEnum.FAIL.getCode());
            actualResp.setMessage("检查用户登录状态失败");
        }
        if (result != null && result.isSuccess()) {
            actualResp.setMessage(Objects.isNull(result.getData()) ? "用户登录已过期" : null);
            actualResp.setState(Objects.isNull(result.getData()) ? UserLoginSeataEnum.FAIL.getCode() : UserLoginSeataEnum.SUCCESS.getCode());
            if (Objects.nonNull(result.getData())) {
                actualResp.setId(String.valueOf(result.getData().getCustomerUserId()));
                actualResp.setUsername(result.getData().getAccountNumber());
            }
        }
        actualResp.setToken(token);
        return actualResp;
    }
    
    @Override
    public GeeTestAdapterRespDTO initGeeTestConfig() {
        GeeTestLib geeTestLib = new GeeTestLib(geeTestProperties);
        int gtServerStatus = geeTestLib.preProcess(new HashMap<>());
        String key = UUID.randomUUID().toString();
        distributedCache.put(key, String.valueOf(gtServerStatus), 360, TimeUnit.SECONDS);
        String resStr = geeTestLib.getResponseStr();
        GeeTestAdapterRespDTO geeTestAdapterRespDTO = JSON.parseObject(resStr, GeeTestAdapterRespDTO.class);
        geeTestAdapterRespDTO.setStatusKey(key);
        return geeTestAdapterRespDTO;
    }
    
    @Override
    public void logout(String accessToken) {
        customerUserRemoteService.logout(accessToken);
    }
}
