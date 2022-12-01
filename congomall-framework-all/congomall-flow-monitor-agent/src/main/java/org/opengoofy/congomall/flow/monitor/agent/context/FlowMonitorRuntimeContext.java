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

package org.opengoofy.congomall.flow.monitor.agent.context;

import com.wujiuye.flow.FlowType;
import com.wujiuye.flow.Flower;
import org.opengoofy.congomall.flow.monitor.agent.common.FlowMonitorFrameTypeEnum;
import org.opengoofy.congomall.flow.monitor.agent.common.SID;
import org.opengoofy.congomall.flow.monitor.agent.storage.FlowMonitorRunState;
import org.opengoofy.congomall.flow.monitor.agent.storage.MicrometerStorageMode;
import org.opengoofy.congomall.flow.monitor.agent.toolkit.Environments;
import org.springframework.util.AntPathMatcher;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 微服务流量监控运行上下文
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class FlowMonitorRuntimeContext {
    
    /**
     * Map<目标端URI, Map<来源应用, Map<Host, 来源应用详细信息>>>
     */
    private final static Map<String, Map<String, Map<String, FlowMonitorEntity>>> STORAGE = new ConcurrentHashMap<>();
    
    /**
     * 定时任务线程池刷新内存数据持久化
     */
    private final static ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(1);
    
    /**
     * 存储 MVC {@link @Advice.OnMethodEnter} 与 {@link @Advice.OnMethodExit}
     */
    public final static ThreadLocal<Long> EXECUTE_TIME_THREADLOCAL = new ThreadLocal();
    
    /**
     * 是否执行
     */
    public final static ThreadLocal<Boolean> IS_EXECUTE_THREADLOCAL = new ThreadLocal();
    
    /**
     * 来源客户端所有虚拟 URI
     * <p>
     * Actual: /api/order-service/info/{orderId}
     * Virtual: /api/order-service/info/*
     * </p>
     */
    public final static Set<String> CONSUMER_ALL_VIRTUAL_URIS = new HashSet<>();
    
    /**
     * 目标客户端所有虚拟 URI
     * <p>
     * Actual: /api/message-service/info/{orderId}
     * Virtual: /api/message-service/info/*
     * </p>
     */
    public final static Set<String> PROVIDER_ALL_VIRTUAL_URIS = new HashSet<>();
    
    /**
     * 流量监控框架标识
     */
    public final static ThreadLocal<FlowMonitorFrameTypeEnum> FRAME_TYPE_THREADLOCAL = new ThreadLocal();
    
    /**
     * Stream RocketMQ 生产消息类路径
     */
    public final static ThreadLocal<String> STREAM_ROCKETMQ_PROVIDE_CLASS = new ThreadLocal();
    
    /**
     * 初始化行为
     */
    public static void init() {
        SCHEDULED_EXECUTOR_SERVICE.scheduleWithFixedDelay(() -> STORAGE.forEach((interfaceKey, val) -> {
            System.out.println("------------------------------------------");
            System.out.println(String.format("------------ 目标接口: %s", interfaceKey));
            val.forEach((sourceApplication, hosts) -> hosts.forEach((host, param) -> {
                System.out.println(String.format("------------ 来源应用: %s", sourceApplication));
                System.out.println(String.format("------------ 来源接口: %s", param.getSourceResource()));
                System.out.println(String.format("------------ 来源 Host: %s", host));
                System.out.println("------------------------------------------");
                Flower flower = param.getFlowHelper().getFlow(FlowType.Minute);
                System.out.println("总请求数: " + flower.total());
                System.out.println("成功请求数: " + flower.totalSuccess());
                System.out.println("异常请求数: " + flower.totalException());
                System.out.println("平均请求耗时: " + flower.avgRt());
                System.out.println("最大请求耗时: " + flower.maxRt());
                System.out.println("最小请求耗时: " + flower.minRt());
                System.out.println("平均请求成功数: " + flower.successAvg());
                System.out.println("平均请求异常数: " + flower.exceptionAvg());
                FlowMonitorRunState runState = FlowMonitorRunState.builder()
                        .targetApplication(Environments.getApplicationName())
                        .targetIpPort(SID.getIpAddressAndPort())
                        .targetResource(interfaceKey)
                        .sourceApplication(sourceApplication)
                        .sourceIpPort(host)
                        .sourceResource(param.getSourceResource())
                        .total(flower.total())
                        .totalSuccess(flower.totalSuccess())
                        .totalException(flower.totalException())
                        .avgRt(flower.avgRt())
                        .maxRt(flower.maxRt())
                        .minRt(flower.minRt())
                        .successAvg(flower.successAvg())
                        .exceptionAvg(flower.exceptionAvg())
                        .build();
                MicrometerStorageMode.execute(runState);
            }));
        }), 0, 60, TimeUnit.SECONDS);
    }
    
    public static void putApplications(String targetUri, Map<String, Map<String, FlowMonitorEntity>> value) {
        if (getApplications(targetUri) == null) {
            synchronized (FlowMonitorRuntimeContext.class) {
                if (getApplications(targetUri) == null) {
                    STORAGE.put(targetUri, value);
                }
            }
        }
    }
    
    public static Map<String, Map<String, FlowMonitorEntity>> getApplications(String targetUri) {
        return STORAGE.get(targetUri);
    }
    
    public static void putHosts(String targetUri, String applicationName, Map<String, FlowMonitorEntity> value) {
        if (getHosts(targetUri, applicationName) == null) {
            synchronized (FlowMonitorRuntimeContext.class) {
                if (getHosts(targetUri, applicationName) == null) {
                    STORAGE.get(targetUri).put(applicationName, value);
                }
            }
        }
    }
    
    public static Map<String, FlowMonitorEntity> getHosts(String targetUri, String applicationName) {
        return STORAGE.get(targetUri).get(applicationName);
    }
    
    public static void putHost(String targetUri, String applicationName, String host, FlowMonitorEntity param) {
        STORAGE.get(targetUri).get(applicationName).put(host, param);
    }
    
    public static FlowMonitorEntity getHost(String targetUri, String applicationName, String host) {
        return STORAGE.get(targetUri).get(applicationName).get(host);
    }
    
    public static void setExecuteTime() {
        EXECUTE_TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    
    public static long getExecuteTime() {
        return EXECUTE_TIME_THREADLOCAL.get();
    }
    
    public static void setIsExecute(boolean actual) {
        IS_EXECUTE_THREADLOCAL.set(actual);
    }
    
    public static boolean getIsExecute() {
        return Optional.ofNullable(IS_EXECUTE_THREADLOCAL.get()).orElse(false);
    }
    
    public static void setFrameType(FlowMonitorFrameTypeEnum frameTypeEnum) {
        FRAME_TYPE_THREADLOCAL.set(frameTypeEnum);
    }
    
    public static FlowMonitorFrameTypeEnum getFrameType() {
        return FRAME_TYPE_THREADLOCAL.get();
    }
    
    public static String getConsumerVirtualUri(String actualUri) {
        return getVirtualUri(actualUri, CONSUMER_ALL_VIRTUAL_URIS);
    }
    
    public static String getProvideVirtualUri(String actualUri) {
        return getVirtualUri(actualUri, PROVIDER_ALL_VIRTUAL_URIS);
    }
    
    public static String getVirtualUri(String actualUri, Set<String> virtualUris) {
        AntPathMatcher matcher = new AntPathMatcher();
        for (String each : virtualUris) {
            if (matcher.match(each, actualUri)) {
                return each;
            }
        }
        return "Virtual map URI not found, Actual uri: " + actualUri;
    }
    
    public static void removeContent() {
        EXECUTE_TIME_THREADLOCAL.remove();
        IS_EXECUTE_THREADLOCAL.remove();
        FRAME_TYPE_THREADLOCAL.remove();
        STREAM_ROCKETMQ_PROVIDE_CLASS.remove();
    }
}
