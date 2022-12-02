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

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.provider.FlowMonitorSourceParamProviderFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SpringCloud RocketMQ 生产端流量拦截
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class StreamRocketMQProviderInterceptor {
    
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Throwable {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (Objects.equals("org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService$ConsumeRequest", stackTrace[stackTrace.length - 6].getClassName())) {
            return callable.call();
        }
        StackTraceElement stackTraceElement = stackTrace[5];
        FlowMonitorRuntimeContext.setFrameType(FlowMonitorFrameTypeEnum.STREAM_ROCKETMQ_PROVIDER);
        String key = new StringBuilder("/Provide/")
                .append(stackTraceElement.getFileName().substring(0, stackTraceElement.getFileName().length() - 5))
                .append("/")
                .append(stackTraceElement.getMethodName())
                .toString();
        FlowMonitorEntity sourceParam = FlowMonitorSourceParamProviderFactory.getInstance(key);
        Map<String, Map<String, FlowMonitorEntity>> applications = FlowMonitorRuntimeContext.getApplications(sourceParam.getTargetResource());
        if (applications == null) {
            Map<String, Map<String, FlowMonitorEntity>> sourceApplications = new ConcurrentHashMap<>();
            Map<String, FlowMonitorEntity> hosts = new ConcurrentHashMap<>();
            hosts.put(sourceParam.getSourceIpPort(), sourceParam);
            sourceApplications.put(sourceParam.getSourceApplication(), hosts);
            FlowMonitorRuntimeContext.putApplications(sourceParam.getTargetResource(), sourceApplications);
        }
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            return result = callable.call();
        } finally {
            FlowMonitorEntity entity = FlowMonitorRuntimeContext.getHost(key, "Internal call", "Unknown");
            if (result == null || !((boolean) result)) {
                entity.getFlowHelper().incrException();
            } else {
                entity.getFlowHelper().incrSuccess(System.currentTimeMillis() - startTime);
            }
        }
    }
}
