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

import org.opengoofy.congomall.flow.monitor.plugin.enhancer.SpringApplicationInstrumentation;
import org.opengoofy.congomall.flow.monitor.plugin.enhancer.SpringApplicationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Spring 应用上下文代理
 * 应用于 Agent 运行时获取 Spring 上下文中 Bean 对象
 *
 * <p>
 * 通过增强 SpringApplication，执行 {@link SpringApplication#run(String...)} 方法后拿到对应容器赋值 {@link ApplicationContextHolderProxy#applicationContext}
 * 切面以及增强查看 {@link SpringApplicationInstrumentation} {@link SpringApplicationInterceptor}
 * </p>
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class ApplicationContextHolderProxy {
    
    private static ApplicationContext applicationContext;
    
    /**
     * 根据类型获取 IOC 容器 Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
    
    /**
     * 根据名称 & 类型获取 IOC 容器 Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
    
    /**
     * 根据类型获取一组 IOC 容器 Bean
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
    
    /**
     * 查找 Bean 是否有注释
     */
    public static <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return applicationContext.findAnnotationOnBean(beanName, annotationType);
    }
    
    /**
     * 获取 Spring 应用上下文
     */
    public static ApplicationContext getInstance() {
        return applicationContext;
    }
    
    /**
     * 初始化上下文
     */
    public static void initContext(ApplicationContext context) {
        applicationContext = context;
    }
}
