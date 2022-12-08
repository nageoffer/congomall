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

import net.bytebuddy.implementation.bind.annotation.*;
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.provider.FlowMonitorSourceParamProviderFactory;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Reflects;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.SystemClock;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * XXL-Job 任务执行拦截
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public class XXLJobInterceptor {
    
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @This Object obj,
                                   @AllArguments Object[] allArguments,
                                   @SuperCall Callable<?> callable) throws Throwable {
        String key = new StringBuilder("/")
                .append(Reflects.getFieldValue(obj, "target").getClass().getSimpleName())
                .append("/")
                .append(((Method) Reflects.getFieldValue(obj, "method")).getName())
                .toString();
        FlowMonitorEntity sourceParam = FlowMonitorSourceParamProviderFactory.createInstance(key, FlowMonitorFrameTypeEnum.XXL_JOB);
        XXLJobInterceptor.loadResource(sourceParam);
        boolean exFlag = false;
        long startTime = SystemClock.now();
        try {
            return callable.call();
        } catch (Throwable ex) {
            exFlag = true;
            throw ex;
        } finally {
            FlowMonitorEntity flowMonitorEntity = FlowMonitorRuntimeContext.getHost(sourceParam.getTargetResource(), sourceParam.getSourceApplication(), sourceParam.getSourceIpPort());
            if (exFlag) {
                flowMonitorEntity.getFlowHelper().incrSuccess(SystemClock.now() - startTime);
            } else {
                flowMonitorEntity.getFlowHelper().incrException();
            }
        }
    }
    
    private static void loadResource(FlowMonitorEntity sourceParam) {
        Map<String, Map<String, FlowMonitorEntity>> applications = FlowMonitorRuntimeContext.getApplications(sourceParam.getTargetResource());
        if (applications == null) {
            Map<String, Map<String, FlowMonitorEntity>> sourceApplications = new ConcurrentHashMap<>();
            Map<String, FlowMonitorEntity> hosts = new ConcurrentHashMap<>();
            hosts.put(sourceParam.getSourceIpPort(), sourceParam);
            sourceApplications.put(sourceParam.getSourceApplication(), hosts);
            FlowMonitorRuntimeContext.putApplications(sourceParam.getTargetResource(), sourceApplications);
        }
    }
}
