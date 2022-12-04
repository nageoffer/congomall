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
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Base64;

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
     * @param customerTargetResource 自定义目标客户端资源信息, eg: XXL-Job、RocketMQ...
     * @return
     */
    public static FlowMonitorEntity getInstance(final String customerTargetResource) {
        return getInstance(customerTargetResource, null);
    }
    
    /**
     * 获取实例
     *
     * @param httpServletRequest Http 请求头
     * @return
     */
    public static FlowMonitorEntity getInstance(final HttpServletRequest httpServletRequest) {
        return getInstance(null, httpServletRequest);
    }
    
    /**
     * 获取实例
     *
     * @param customerTargetResource 自定义目标客户端资源信息, eg: XXL-Job、RocketMQ...
     * @param httpServletRequest     Http 请求头
     * @return
     */
    public static FlowMonitorEntity getInstance(final String customerTargetResource, final HttpServletRequest httpServletRequest) {
        return buildInstance(customerTargetResource, httpServletRequest, false);
    }
    
    /**
     * 创建实例
     *
     * @param customerTargetResource 自定义目标客户端资源信息, eg: XXL-Job、RocketMQ...
     * @return
     */
    public static FlowMonitorEntity createInstance(final String customerTargetResource) {
        return createInstance(customerTargetResource, null);
    }
    
    /**
     * 创建实例
     *
     * @param httpServletRequest Http 请求头
     * @return
     */
    public static FlowMonitorEntity createInstance(final HttpServletRequest httpServletRequest) {
        return createInstance(null, httpServletRequest);
    }
    
    /**
     * 创建实例
     *
     * @param customerTargetResource 自定义目标客户端资源信息, eg: XXL-Job、RocketMQ...
     * @param httpServletRequest     Http 请求头
     * @return
     */
    public static FlowMonitorEntity createInstance(final String customerTargetResource, final HttpServletRequest httpServletRequest) {
        return buildInstance(customerTargetResource, httpServletRequest, true);
    }
    
    /**
     * 构建实例
     *
     * @param customerTargetResource 自定义目标客户端资源信息, eg: XXL-Job、RocketMQ...
     * @param httpServletRequest     Http 请求头
     * @param createFlag             创建标识
     * @return
     */
    private static FlowMonitorEntity buildInstance(final String customerTargetResource, final HttpServletRequest httpServletRequest, final boolean createFlag) {
        String requestMethod;
        String sourceApplication;
        String sourceResource;
        String sourceIpPort;
        String targetResource;
        String flowMonitorType;
        FlowMonitorFrameTypeEnum frameType = FlowMonitorRuntimeContext.getFrameType();
        if (frameType == FlowMonitorFrameTypeEnum.XXL_JOB) {
            sourceApplication = "Internal call";
            sourceResource = "Unknown";
            sourceIpPort = "Unknown";
            requestMethod = "Unknown";
            targetResource = customerTargetResource;
            flowMonitorType = "XXL-Job";
        } else if (frameType == FlowMonitorFrameTypeEnum.STREAM_ROCKETMQ_CONSUMER) {
            sourceApplication = "Internal call";
            sourceResource = "Unknown";
            sourceIpPort = "Unknown";
            requestMethod = "Unknown";
            targetResource = customerTargetResource;
            flowMonitorType = "RocketMQ";
        } else if (frameType == FlowMonitorFrameTypeEnum.STREAM_ROCKETMQ_PROVIDER) {
            sourceApplication = "Internal call";
            sourceResource = "Unknown";
            sourceIpPort = "Unknown";
            requestMethod = "Unknown";
            targetResource = customerTargetResource;
            flowMonitorType = "RocketMQ";
        } else if (httpServletRequest.getHeader("sw8") != null) {
            // SkyWalking Resource
            requestMethod = "Unknown";
            targetResource = httpServletRequest.getRequestURI();
            String[] sw8s = httpServletRequest.getHeader("sw8").split("-");
            sourceIpPort = Base64.decodeStr(sw8s[sw8s.length - 1]);
            sourceResource = Base64.decodeStr(sw8s[sw8s.length - 2]);
            sourceApplication = Base64.decodeStr(sw8s[4]);
            flowMonitorType = "API";
            targetResource = FlowMonitorRuntimeContext.getProvideVirtualUri(targetResource);
        } else {
            if (httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_METHOD).hasMoreElements()) {
                requestMethod = httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_METHOD).nextElement();
            } else {
                requestMethod = httpServletRequest.getMethod();
            }
            if (httpServletRequest.getHeaders(SOURCE_APPLICATION_NAME).hasMoreElements()) {
                sourceApplication = httpServletRequest.getHeaders(SOURCE_APPLICATION_NAME).nextElement();
            } else {
                sourceApplication = "Gateway";
            }
            if (httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_URI).hasMoreElements()) {
                sourceResource = httpServletRequest.getHeaders(SOURCE_HTTP_REQUEST_URI).nextElement();
            } else {
                sourceResource = "Unknown";
            }
            if (httpServletRequest.getHeaders(SOURCE_HTTP_HOST).hasMoreElements()) {
                sourceIpPort = httpServletRequest.getHeaders(SOURCE_HTTP_HOST).nextElement();
            } else {
                sourceIpPort = "Unknown";
            }
            if (httpServletRequest.getHeaders(TARGET_HTTP_REQUEST_URI).hasMoreElements()) {
                targetResource = httpServletRequest.getHeaders(TARGET_HTTP_REQUEST_URI).nextElement();
            } else {
                targetResource = httpServletRequest.getRequestURI();
            }
            flowMonitorType = "API";
            targetResource = FlowMonitorRuntimeContext.getProvideVirtualUri(targetResource);
        }
        FlowMonitorEntity monitorEntity = FlowMonitorEntity.builder()
                .type(flowMonitorType)
                .requestMethod(requestMethod)
                .sourceApplication(sourceApplication)
                .sourceResource(sourceResource)
                .sourceIpPort(sourceIpPort)
                .targetResource(targetResource)
                .build();
        if (createFlag) {
            monitorEntity.setFlowHelper(new FlowHelper(FlowType.Minute));
        }
        return monitorEntity;
    }
}
