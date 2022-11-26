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

package org.opengoofy.congomall.flow.monitor.agent.provider;

import com.wujiuye.flow.FlowHelper;
import com.wujiuye.flow.FlowType;
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorConstant;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorSourceParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 微服务流量监控来源客户端参数提供工厂
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public class FlowMonitorSourceParamProviderFactory {
    
    /**
     * 获取实例
     *
     * @param httpServletRequest Http 请求头
     * @return
     */
    public static FlowMonitorSourceParam getInstance(final HttpServletRequest httpServletRequest) {
        FlowMonitorSourceParam sourceParam = FlowMonitorSourceParam.builder()
                .flowHelper(new FlowHelper(FlowType.Minute))
                .requestMethod(httpServletRequest.getHeaders(FlowMonitorConstant.SOURCE_HTTP_REQUEST_METHOD).nextElement())
                .sourceApplicationName(httpServletRequest.getHeaders(FlowMonitorConstant.SOURCE_APPLICATION_NAME).nextElement())
                .sourceHttpUri(httpServletRequest.getHeaders(FlowMonitorConstant.SOURCE_HTTP_REQUEST_URI).nextElement())
                .sourceHost(httpServletRequest.getHeaders(FlowMonitorConstant.SOURCE_HTTP_HOST).nextElement())
                .targetHttpUri(httpServletRequest.getHeaders(FlowMonitorConstant.TARGET_HTTP_REQUEST_URI).nextElement())
                .build();
        return sourceParam;
    }
}
