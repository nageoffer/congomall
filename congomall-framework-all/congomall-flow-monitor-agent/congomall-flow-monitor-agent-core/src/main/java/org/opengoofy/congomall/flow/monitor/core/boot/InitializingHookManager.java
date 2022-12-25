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

package org.opengoofy.congomall.flow.monitor.core.boot;

import org.opengoofy.congomall.flow.monitor.core.loader.AgentPluginClassLoader;
import org.opengoofy.congomall.flow.monitor.core.logging.Logger;

import java.util.*;

/**
 * 初始化钩子函数管理
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public enum InitializingHookManager {
    
    INSTANCE;
    
    private Map<Class, InitializingHook> initializingHooks = Collections.emptyMap();
    
    public void boot() {
        initializingHooks = loadInitializingHooks(AgentPluginClassLoader.getDefault());
        Logger.info("Execute the initialization hook function. Hooks: {%d}", initializingHooks.size());
        initializingHooks.values().forEach(each -> {
            try {
                each.afterAgentPremain();
            } catch (Exception ignored) {
            }
        });
    }
    
    public Map<Class, InitializingHook> loadInitializingHooks(ClassLoader classLoader) {
        Map<Class, InitializingHook> allInitializingHooks = new LinkedHashMap<>();
        for (InitializingHook initializingHook : ServiceLoader.load(InitializingHook.class)) {
            allInitializingHooks.put(initializingHook.getClass(), initializingHook);
        }
        return allInitializingHooks;
    }
}
