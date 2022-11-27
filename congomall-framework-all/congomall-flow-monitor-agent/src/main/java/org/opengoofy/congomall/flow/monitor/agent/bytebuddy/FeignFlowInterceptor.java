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

import feign.Request;
import net.bytebuddy.asm.Advice;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorVirtualUriLoader;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Lists;
import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorConstant.*;

/**
 * Feign 流量拦截
 */
public final class FeignFlowInterceptor {
    
    @Advice.OnMethodEnter
    public static void enter(@Advice.This Object obj,
                             @Advice.AllArguments Object[] allArguments,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName) throws Throwable {
        FlowMonitorVirtualUriLoader.loadConsumerUris();
        Request request = (Request) allArguments[0];
        Field headersField = Request.class.getDeclaredField("headers");
        headersField.setAccessible(true);
        Map<String, Collection<String>> headers = new LinkedHashMap();
        String applicationName = ApplicationContextHolder.getBean(ConfigurableEnvironment.class).getProperty(SPRING_APPLICATION_NAME, "");
        headers.put(SOURCE_APPLICATION_NAME, Lists.newArrayList(applicationName));
        headersField.set(request, Collections.unmodifiableMap(headers));
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            headers.put(SOURCE_HTTP_REQUEST_METHOD, Lists.newArrayList(servletRequestAttributes.getRequest().getMethod()));
            headers.put(SOURCE_HTTP_REQUEST_URI, Lists.newArrayList(FlowMonitorRuntimeContext.getConsumerVirtualUri(servletRequestAttributes.getRequest().getRequestURI())));
        } catch (Exception ignored) {
        }
        URL url = new URL(request.url());
        headers.put(SOURCE_HTTP_HOST, Lists.newArrayList(String.format("%s:%d", url.getHost(), url.getPort())));
        headers.put(TARGET_HTTP_REQUEST_URI, Lists.newArrayList(url.getPath()));
    }
}
