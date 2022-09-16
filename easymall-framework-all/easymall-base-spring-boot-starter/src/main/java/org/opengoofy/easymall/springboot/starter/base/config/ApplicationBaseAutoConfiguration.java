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

package org.opengoofy.easymall.springboot.starter.base.config;

import org.opengoofy.easymall.springboot.starter.base.ApplicationContextHolder;
import org.opengoofy.easymall.springboot.starter.base.init.ApplicationContentPostProcessor;
import org.opengoofy.easymall.springboot.starter.base.safa.FastJsonSafeMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * 应用基础自动装配
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
public class ApplicationBaseAutoConfiguration {
    
    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }
    
    @Bean
    public ApplicationContentPostProcessor applicationContentPostProcessor() {
        return new ApplicationContentPostProcessor();
    }
    
    @Bean
    @ConditionalOnProperty(value = "fastjson.safa-mode", havingValue = "true")
    public FastJsonSafeMode fastJsonSafeMode() {
        return new FastJsonSafeMode();
    }
}
