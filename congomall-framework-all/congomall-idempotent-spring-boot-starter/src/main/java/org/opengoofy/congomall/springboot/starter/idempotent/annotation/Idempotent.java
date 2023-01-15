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

package org.opengoofy.congomall.springboot.starter.idempotent.annotation;

import org.opengoofy.congomall.springboot.starter.idempotent.enums.IdempotentTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等注解
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    
    /**
     * 幂等 Key, 只有在 {@link Idempotent#type()} 为 {@link IdempotentTypeEnum#SPEL} 时生效
     */
    String key() default "";
    
    /**
     * 触发幂等失败逻辑时, 返回的错误提示信息
     */
    String message() default "您操作太快，请稍后再试";
    
    /**
     * 验证幂等类型, 支持多种幂等方式
     * Rest API 建议使用 {@link IdempotentTypeEnum#TOKEN} 或 {@link IdempotentTypeEnum#PARAM}
     * 其它类型幂等验证, 使用 {@link IdempotentTypeEnum#SPEL}
     */
    IdempotentTypeEnum type() default IdempotentTypeEnum.PARAM;
}
