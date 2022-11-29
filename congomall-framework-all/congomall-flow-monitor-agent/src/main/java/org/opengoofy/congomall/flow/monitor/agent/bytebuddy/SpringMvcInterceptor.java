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

package org.opengoofy.congomall.flow.monitor.agent.bytebuddy;

import net.bytebuddy.asm.Advice;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorVirtualUriLoader;
import org.opengoofy.congomall.flow.monitor.agent.provider.FlowMonitorSourceParamProviderFactory;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorConstant.SOURCE_APPLICATION_NAME;
import static org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorConstant.SOURCE_GATEWAY_FLAG;

/**
 * Spring MVC 流量拦截
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class SpringMvcInterceptor {
    
    @Advice.OnMethodEnter
    public static void enter(@Advice.This Object obj,
                             @Advice.Argument(0) ServletWebRequest webRequest,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName) throws Throwable {
        HttpServletRequest httpServletRequest = webRequest.getRequest();
        Enumeration<String> sourceApplicationNameEnumeration = httpServletRequest.getHeaders(SOURCE_APPLICATION_NAME);
        Enumeration<String> sourceGatewayEnumeration = httpServletRequest.getHeaders(SOURCE_GATEWAY_FLAG);
        if (!sourceGatewayEnumeration.hasMoreElements() && !sourceApplicationNameEnumeration.hasMoreElements()) {
            FlowMonitorRuntimeContext.setIsExecute(Boolean.FALSE);
            return;
        }
        FlowMonitorVirtualUriLoader.loadProviderUris();
        FlowMonitorRuntimeContext.init();
        FlowMonitorEntity sourceParam = FlowMonitorSourceParamProviderFactory.getInstance(httpServletRequest);
        Map<String, Map<String, FlowMonitorEntity>> sourceApplications;
        if ((sourceApplications = FlowMonitorRuntimeContext.getApplications(sourceParam.getTargetResource())) == null) {
            sourceApplications = new ConcurrentHashMap<>();
            Map<String, FlowMonitorEntity> hosts = new ConcurrentHashMap<>();
            hosts.put(sourceParam.getSourceIpPort(), sourceParam);
            sourceApplications.put(sourceParam.getSourceApplication(), hosts);
            FlowMonitorRuntimeContext.putApplications(sourceParam.getTargetResource(), sourceApplications);
        } else if (FlowMonitorRuntimeContext.getHosts(sourceParam.getTargetResource(), sourceParam.getSourceApplication()) == null) {
            Map<String, FlowMonitorEntity> hosts = new ConcurrentHashMap<>();
            hosts.put(sourceParam.getSourceIpPort(), sourceParam);
            sourceApplications.put(sourceParam.getSourceApplication(), hosts);
            FlowMonitorRuntimeContext.putHosts(sourceParam.getTargetResource(), sourceParam.getSourceApplication(), hosts);
        } else if (FlowMonitorRuntimeContext.getHost(sourceParam.getTargetResource(), sourceParam.getSourceApplication(), sourceParam.getSourceIpPort()) == null) {
            FlowMonitorRuntimeContext.putHost(sourceParam.getTargetResource(), sourceParam.getSourceApplication(), sourceParam.getSourceIpPort(), sourceParam);
        }
        FlowMonitorRuntimeContext.setExecuteTime();
        FlowMonitorRuntimeContext.setIsExecute(Boolean.TRUE);
    }
    
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Argument(0) ServletWebRequest webRequest,
                            @Advice.Thrown Throwable ex) throws Throwable {
        if (!FlowMonitorRuntimeContext.getIsExecute()) {
            return;
        }
        HttpServletRequest httpServletRequest = webRequest.getRequest();
        FlowMonitorEntity instance = FlowMonitorSourceParamProviderFactory.getInstance(httpServletRequest);
        FlowMonitorEntity sourceParam = FlowMonitorRuntimeContext.getHost(instance.getTargetResource(), instance.getSourceApplication(), instance.getSourceIpPort());
        if (ex == null) {
            sourceParam.getFlowHelper().incrSuccess(System.currentTimeMillis() - FlowMonitorRuntimeContext.getExecuteTime());
        } else {
            sourceParam.getFlowHelper().incrException();
        }
        FlowMonitorRuntimeContext.removeContent();
    }
}
