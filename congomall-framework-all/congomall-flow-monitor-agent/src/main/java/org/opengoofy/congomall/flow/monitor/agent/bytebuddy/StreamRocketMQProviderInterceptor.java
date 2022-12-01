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
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorEntity;
import org.opengoofy.congomall.flow.monitor.agent.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.agent.provider.FlowMonitorSourceParamProviderFactory;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Reflects;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SpringCloud RocketMQ 生产端流量拦截
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class StreamRocketMQProviderInterceptor {
    
    @Advice.OnMethodEnter
    public static void enter(@Advice.This Object obj,
                             @Advice.Origin("#m") String methodName) throws Throwable {
        System.out.println(" =========== 1");
        System.out.println("=========== methodName: " + methodName);
        if (!Objects.equals(methodName, "doSend")) {
            return;
        }
        System.out.println("========== 1");
        FlowMonitorRuntimeContext.setFrameType(FlowMonitorFrameTypeEnum.STREAM_ROCKETMQ_PROVIDER);
        String key = new StringBuilder("/RocketMQ/Provide/").append(Reflects.getFieldValue(obj, "beanName")).toString();
        System.out.println("===============2 key: " + key);
        FlowMonitorRuntimeContext.STREAM_ROCKETMQ_PROVIDE_CLASS.set(key);
        FlowMonitorEntity sourceParam = FlowMonitorSourceParamProviderFactory.getInstance(key);
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
        FlowMonitorEntity instance = FlowMonitorSourceParamProviderFactory.getInstance(FlowMonitorRuntimeContext.STREAM_ROCKETMQ_PROVIDE_CLASS.get());
        FlowMonitorEntity sourceParam = FlowMonitorRuntimeContext.getHost(instance.getTargetResource(), instance.getSourceApplication(), instance.getSourceIpPort());
        if (ex == null) {
            sourceParam.getFlowHelper().incrSuccess(System.currentTimeMillis() - FlowMonitorRuntimeContext.getExecuteTime());
        } else {
            sourceParam.getFlowHelper().incrException();
        }
        FlowMonitorRuntimeContext.removeContent();
    }
}
