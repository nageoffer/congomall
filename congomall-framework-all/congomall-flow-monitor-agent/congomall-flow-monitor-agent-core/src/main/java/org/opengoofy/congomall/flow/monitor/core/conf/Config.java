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

package org.opengoofy.congomall.flow.monitor.core.conf;

/**
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public final class Config {
    
    public static class Agent {
        
        public static final String SPRING_MVC_ENHANCE_CLASS = "org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod";
        public static final String SPRING_MVC_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.SpringMvcInstrumentation";
        
        public static final String XXL_JOB_ENHANCE_CLASS = "com.xxl.job.core.handler.impl.MethodJobHandler";
        public static final String XXL_JOB_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.XxlJobInstrumentation";
        
        public static final String OPEN_FEIGN_ENHANCE_CLASS = "feign.Client";
        public static final String OPEN_FEIGN_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.OpenFeignInstrumentation";
        
        public static final String SPRING_CLOUD_STREAM_ROCKETMQ_PROVIDER_ENHANCE_CLASS = "org.springframework.cloud.stream.messaging.DirectWithAttributesChannel";
        public static final String SPRING_CLOUD_STREAM_ROCKETMQ_PROVIDER_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.StreamRocketMQProviderInstrumentation";
        
        public static final String SPRING_CLOUD_STREAM_ROCKETMQ_CONSUMER_ENHANCE_CLASS = "org.springframework.messaging.handler.invocation.InvocableHandlerMethod";
        public static final String SPRING_CLOUD_STREAM_ROCKETMQ_CONSUMER_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.StreamRocketMQConsumerInstrumentation";
        
        public static final String SPRING_APPLICATION_ENHANCE_CLASS = "org.springframework.boot.SpringApplication";
        public static final String SPRING_APPLICATION_ASPECT_CLASS = "org.opengoofy.congomall.flow.monitor.plugin.enhancer.SpringApplicationInstrumentation";
    }
    
    public static class Environment {
        
        public static String SPRING_PROFILES_ACTIVE = "";
    }
}
