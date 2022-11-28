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
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorSourceParam;

import javax.servlet.http.HttpServletRequest;

import static org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorConstant.*;

/**
 * 微服务流量监控来源客户端参数提供工厂
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class FlowMonitorSourceParamProviderFactory {
    
    /**
     * 获取实例
     *
     * @param httpServletRequest Http 请求头
     * @return
     */
    public static FlowMonitorSourceParam getInstance(final HttpServletRequest httpServletRequest) {
        String requestMethod;
        if (httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_METHOD).hasMoreElements()) {
            requestMethod = httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_METHOD).nextElement();
        } else {
            requestMethod = httpServletRequest.getMethod();
        }
        String sourceApplicationName;
        if (httpServletRequest.getHeaders(SOURCE_APPLICATION_NAME).hasMoreElements()) {
            sourceApplicationName = httpServletRequest.getHeaders(SOURCE_APPLICATION_NAME).nextElement();
        } else {
            sourceApplicationName = "Gateway";
        }
        String sourceHttpUri;
        if (httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_URI).hasMoreElements()) {
            sourceHttpUri = httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_URI).nextElement();
        } else {
            sourceHttpUri = "Unknown";
        }
        String sourceHost;
        if (httpServletRequest.getHeaders(SOURCE_HTTP_HOST).hasMoreElements()) {
            sourceHost = httpServletRequest.getHeaders(SOURCE_HTTP_HOST).nextElement();
        } else {
            sourceHost = "Unknown";
        }
        String targetHttpUri;
        if (httpServletRequest.getHeaders(TARGET_HTTP_REQUEST_URI).hasMoreElements()) {
            targetHttpUri = httpServletRequest.getHeaders(TARGET_HTTP_REQUEST_URI).nextElement();
        } else {
            targetHttpUri = httpServletRequest.getRequestURI();
        }
        FlowMonitorSourceParam sourceParam = FlowMonitorSourceParam.builder()
                .flowHelper(new FlowHelper(FlowType.Minute))
                .requestMethod(requestMethod)
                .sourceApplicationName(sourceApplicationName)
                .sourceHttpUri(sourceHttpUri)
                .sourceHost(sourceHost)
                .targetHttpUri(FlowMonitorRuntimeContext.getProvideVirtualUri(targetHttpUri))
                .build();
        return sourceParam;
    }
}
