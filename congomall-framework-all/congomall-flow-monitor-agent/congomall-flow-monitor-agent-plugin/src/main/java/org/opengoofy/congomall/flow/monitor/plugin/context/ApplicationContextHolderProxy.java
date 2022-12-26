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

package org.opengoofy.congomall.flow.monitor.plugin.context;

import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public class ApplicationContextHolderProxy {
    
    /**
     * 根据类型获取 IOC 容器 Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return ApplicationContextHolder.getBean(clazz);
    }
    
    /**
     * 根据名称 & 类型获取 IOC 容器 Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return ApplicationContextHolder.getBean(name, clazz);
    }
    
    /**
     * 根据类型获取一组 IOC 容器 Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return ApplicationContextHolder.getBeansOfType(clazz);
    }
    
    /**
     * Find whether the bean has annotations.
     *
     * @param beanName
     * @param annotationType
     * @param <A>
     * @return
     */
    public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return ApplicationContextHolder.findAnnotationOnBean(beanName, annotationType);
    }
    
    /**
     * Get ApplicationContext.
     *
     * @return
     */
    public static ApplicationContext getInstance() {
        return ApplicationContextHolder.getInstance();
    }
}
