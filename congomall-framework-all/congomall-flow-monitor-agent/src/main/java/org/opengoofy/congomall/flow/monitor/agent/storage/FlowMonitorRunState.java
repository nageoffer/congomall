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

package org.opengoofy.congomall.flow.monitor.agent.storage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微服务流量监控运行数据
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class FlowMonitorRunState implements Serializable {
    
    /**
     * 来源应用名
     */
    private String sourceApplicationName;
    
    /**
     * 来源 Host
     */
    private String sourceHost;
    
    /**
     * 来源请求 URI
     */
    private String sourceHttpUri;
    
    /**
     * 目标应用名
     */
    private String targetApplicationName;
    
    /**
     * 目标 Host
     */
    private String targetHost;
    
    /**
     * 目标请求 URI
     */
    private String targetHttpUri;
    
    /**
     * 全部请求数
     */
    private Long total;
    
    /**
     * 全部成功数
     */
    private Long totalSuccess;
    
    /**
     * 全部异常数
     */
    private Long totalException;
    
    /**
     * 平均请求成功数
     */
    private Float successAvg;
    
    /**
     * 平均请求异常数
     */
    private Long exceptionAvg;
    
    /**
     * 平均请求耗时
     */
    private Long avgRt;
    
    /**
     * 最小请求耗时
     */
    private Long minRt;
    
    /**
     * 最大请求耗时
     */
    private Long maxRt;
}
