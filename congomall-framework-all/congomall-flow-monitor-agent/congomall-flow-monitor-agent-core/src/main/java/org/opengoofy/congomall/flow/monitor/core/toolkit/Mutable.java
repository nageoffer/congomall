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

package org.opengoofy.congomall.flow.monitor.core.toolkit;

/**
 * 提供可变值类型接口
 *
 * @param <T> 值得类型
 * @since 3.0.1
 */
public interface Mutable<T> {
    
    /**
     * 获得原始值
     *
     * @return 原始值
     */
    T get();
    
    /**
     * 设置值
     *
     * @param value 值
     */
    void set(T value);
    
}