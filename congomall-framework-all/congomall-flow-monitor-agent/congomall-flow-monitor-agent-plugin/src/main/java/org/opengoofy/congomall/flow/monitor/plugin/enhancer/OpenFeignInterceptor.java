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

package org.opengoofy.congomall.flow.monitor.plugin.enhancer;

import feign.Request;
import org.opengoofy.congomall.flow.monitor.core.define.InstanceMethodsAroundInterceptor;
import org.opengoofy.congomall.flow.monitor.core.toolkit.Lists;
import org.opengoofy.congomall.flow.monitor.plugin.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.plugin.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.plugin.context.FlowMonitorVirtualUriLoader;
import org.opengoofy.congomall.flow.monitor.plugin.toolkit.Environments;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.opengoofy.congomall.flow.monitor.plugin.common.FlowMonitorConstant.*;

/**
 * OpenFeign 增强
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public final class OpenFeignInterceptor implements InstanceMethodsAroundInterceptor {
    
    private static final String HEADERS = "headers";
    
    @Override
    public void beforeMethod(Object obj, Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable {
        FlowMonitorRuntimeContext.pushEnhancerType(FlowMonitorFrameTypeEnum.FEIGN);
        FlowMonitorVirtualUriLoader.loadConsumerUris();
        Request request = (Request) allArguments[0];
        Field headersField = Request.class.getDeclaredField(HEADERS);
        headersField.setAccessible(true);
        Object requestHeaders = headersField.get(request);
        Map<String, Collection<String>> headers = new LinkedHashMap();
        if (requestHeaders != null) {
            headers.putAll((Map<String, Collection<String>>) requestHeaders);
        }
        String applicationName = Environments.getApplicationName();
        headers.put(SOURCE_APPLICATION_NAME, Lists.newArrayList(applicationName));
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            headers.put(SOURCE_HTTP_REQUEST_METHOD, Lists.newArrayList(servletRequestAttributes.getRequest().getMethod()));
            headers.put(SOURCE_HTTP_REQUEST_URI, Lists.newArrayList(FlowMonitorRuntimeContext.getConsumerVirtualUri(servletRequestAttributes.getRequest().getRequestURI())));
        } catch (Exception ignored) {
        }
        URL url = new URL(request.url());
        headers.put(SOURCE_HTTP_HOST, Lists.newArrayList(String.format("%s:%d", url.getHost(), url.getPort())));
        headers.put(TARGET_HTTP_REQUEST_URI, Lists.newArrayList(url.getPath()));
        headersField.set(request, Collections.unmodifiableMap(headers));
    }
    
    @Override
    public void afterMethod(Object obj, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object result, Throwable ex) throws Throwable {
        
    }
}
