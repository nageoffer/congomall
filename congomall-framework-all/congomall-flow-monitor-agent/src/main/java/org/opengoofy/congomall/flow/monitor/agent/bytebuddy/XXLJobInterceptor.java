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
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.provider.FlowMonitorSourceParamProviderFactory;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Reflects;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * XXL-Job 任务执行拦截
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public class XXLJobInterceptor {
    
    @Advice.OnMethodEnter
    public static void enter(@Advice.This Object obj,
                             @Advice.Origin("#t") String className,
                             @Advice.Origin("#m") String methodName) throws Throwable {
        FlowMonitorRuntimeContext.setFrameType(FlowMonitorFrameTypeEnum.XXL_JOB);
        String key = new StringBuilder("/")
                .append(Reflects.getFieldValue(obj, "target").getClass().getSimpleName())
                .append("/")
                .append(((Method) Reflects.getFieldValue(obj, "method")).getName())
                .toString();
        FlowMonitorEntity sourceParam = FlowMonitorSourceParamProviderFactory.createInstance(key);
        Map<String, Map<String, FlowMonitorEntity>> applications = FlowMonitorRuntimeContext.getApplications(sourceParam.getTargetResource());
        if (applications == null) {
            Map<String, Map<String, FlowMonitorEntity>> sourceApplications = new ConcurrentHashMap<>();
            Map<String, FlowMonitorEntity> hosts = new ConcurrentHashMap<>();
            hosts.put(sourceParam.getSourceIpPort(), sourceParam);
            sourceApplications.put(sourceParam.getSourceApplication(), hosts);
            FlowMonitorRuntimeContext.putApplications(sourceParam.getTargetResource(), sourceApplications);
        }
        FlowMonitorRuntimeContext.setExecuteTime();
        FlowMonitorRuntimeContext.setIsExecute(Boolean.TRUE);
    }
    
    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.This Object obj,
                            @Advice.Thrown Throwable ex) throws Throwable {
        if (!FlowMonitorRuntimeContext.getIsExecute()) {
            return;
        }
        String key = new StringBuilder()
                .append(Reflects.getFieldValue(obj, "target").getClass().getSimpleName())
                .append("/")
                .append(((Method) Reflects.getFieldValue(obj, "method")).getName())
                .toString();
        FlowMonitorEntity instance = FlowMonitorSourceParamProviderFactory.getInstance(key);
        FlowMonitorEntity sourceParam = FlowMonitorRuntimeContext.getHost(instance.getTargetResource(), instance.getSourceApplication(), instance.getSourceIpPort());
        if (ex == null) {
            sourceParam.getFlowHelper().incrSuccess(System.currentTimeMillis() - FlowMonitorRuntimeContext.getExecuteTime());
        } else {
            sourceParam.getFlowHelper().incrException();
        }
        FlowMonitorRuntimeContext.removeContent();
    }
}
