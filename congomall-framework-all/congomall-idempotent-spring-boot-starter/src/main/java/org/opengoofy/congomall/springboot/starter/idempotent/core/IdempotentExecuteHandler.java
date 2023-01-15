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

package org.opengoofy.congomall.springboot.starter.idempotent.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.opengoofy.congomall.springboot.starter.idempotent.annotation.Idempotent;

/**
 * 幂等执行处理器
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public interface IdempotentExecuteHandler {
    
    /**
     * 幂等处理逻辑
     *
     * @param wrapper 幂等参数包装器
     */
    void handler(IdempotentParamWrapper wrapper);
    
    /**
     * 执行幂等处理逻辑
     *
     * @param joinPoint  AOP 方法处理
     * @param idempotent 幂等注解
     */
    void execute(ProceedingJoinPoint joinPoint, Idempotent idempotent);
    
    /**
     * 后置处理
     */
    default void postProcessing() {
        
    }
}
