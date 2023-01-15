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

import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.opengoofy.congomall.springboot.starter.idempotent.enums.IdempotentTypeEnum;

/**
 * 幂等执行处理器工厂
 * <p>
 * Q：可能会有同学有疑问：这里为什么要采用简单工厂模式？策略模式不行么？
 * A：策略模式同样可以达到获取真正幂等处理器功能。只是为了模拟更多设计模式，所以选择了简单工厂
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class IdempotentExecuteHandlerFactory {
    
    /**
     * 获取幂等执行处理器
     *
     * @param type 指定幂等处理类型
     * @return 幂等执行处理器
     */
    public static IdempotentExecuteHandler getInstance(IdempotentTypeEnum type) {
        IdempotentExecuteHandler result = null;
        switch (type) {
            case SPEL:
                result = ApplicationContextHolder.getBean(IdempotentSPELExecuteHandler.class);
                break;
            case PARAM:
                result = ApplicationContextHolder.getBean(IdempotentParamExecuteHandler.class);
                break;
            case TOKEN:
                result = ApplicationContextHolder.getBean(IdempotentTokenExecuteHandler.class);
                break;
            default:
                break;
        }
        return result;
    }
}
