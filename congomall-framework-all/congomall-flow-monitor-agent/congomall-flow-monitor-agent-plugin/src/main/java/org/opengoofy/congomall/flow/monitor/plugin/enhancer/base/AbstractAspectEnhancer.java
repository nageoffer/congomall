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

package org.opengoofy.congomall.flow.monitor.plugin.enhancer.base;

import org.opengoofy.congomall.flow.monitor.core.aspect.IAspectEnhancer;
import org.opengoofy.congomall.flow.monitor.plugin.context.FlowMonitorRuntimeContext;
import org.opengoofy.congomall.flow.monitor.plugin.writer.FlowMonitorWrite;

import java.lang.reflect.Method;

/**
 * 抽象类切面增强定义
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public abstract class AbstractAspectEnhancer implements IAspectEnhancer {
    
    protected abstract void beforeMethodExecute(Object obj,
                                                Method method,
                                                Object[] allArguments,
                                                Class<?>[] argumentsTypes) throws Throwable;
    
    protected abstract void afterMethodExecute(Object obj,
                                               Method method,
                                               Object[] allArguments, Class<?>[] argumentsTypes,
                                               Object result,
                                               Throwable ex) throws Throwable;
    
    @Override
    public void beforeMethod(Object obj, Method method, Object[] allArguments, Class<?>[] argumentsTypes) throws Throwable {
        FlowMonitorWrite.initScheduleWriteData();
        beforeMethodExecute(obj, method, allArguments, argumentsTypes);
    }
    
    @Override
    public void afterMethod(Object obj, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object result, Throwable ex) throws Throwable {
        Long executeTime = FlowMonitorRuntimeContext.getExecuteTime();
        if (executeTime == null) {
            return;
        }
        afterMethodExecute(obj, method, allArguments, argumentsTypes, result, ex);
        FlowMonitorRuntimeContext.removeContent();
    }
}
