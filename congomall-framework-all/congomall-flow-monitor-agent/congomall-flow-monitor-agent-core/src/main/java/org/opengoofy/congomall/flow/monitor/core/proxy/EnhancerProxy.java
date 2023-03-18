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

package org.opengoofy.congomall.flow.monitor.core.proxy;

import net.bytebuddy.implementation.bind.annotation.*;
import org.opengoofy.congomall.flow.monitor.core.define.InstanceMethodsAroundInterceptor;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 增强代理
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class EnhancerProxy {
    
    public InstanceMethodsAroundInterceptor enhancer;
    
    @IgnoreForBinding
    public void setEnhancer(InstanceMethodsAroundInterceptor enhancer) {
        this.enhancer = enhancer;
    }
    
    @RuntimeType
    @BindingPriority(value = 1)
    public Object intercept(@This Object obj, @AllArguments Object[] allArguments, @SuperCall Callable<?> callable,
                            @Origin Method method) throws Throwable {
        try {
            enhancer.beforeMethod(obj, method, allArguments, method.getParameterTypes());
        } catch (Throwable ignored) {
        }
        Object result = null;
        Throwable callableEx = null;
        try {
            result = callable.call();
        } catch (Throwable ex) {
            callableEx = ex;
            throw ex;
        } finally {
            try {
                enhancer.afterMethod(obj, method, allArguments, method.getParameterTypes(), result, callableEx);
            } catch (Throwable ignored) {
            }
        }
        return result;
    }
}
