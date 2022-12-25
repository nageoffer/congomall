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

package org.opengoofy.congomall.flow.monitor.core.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 自定义代理插件类加载器
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public class AgentPluginClassLoader extends ClassLoader {
    
    private static AgentPluginClassLoader DEFAULT_LOADER;
    private static String PLUGIN_FILE_PATH =
            "/Users/single/workspace/generic/congomall/congomall-framework-all/congomall-flow-monitor-agent/congomall-flow-monitor-agent-plugin/target/flow-monitor-agent-plugin.jar";
    
    private List<File> classpath;
    private List<Jar> allJars = new LinkedList<>();
    
    public static void initDefaultLoader() {
        if (DEFAULT_LOADER == null) {
            synchronized (AgentPluginClassLoader.class) {
                if (DEFAULT_LOADER == null) {
                    DEFAULT_LOADER = new AgentPluginClassLoader(AgentPluginClassLoader.class.getClassLoader());
                }
            }
        }
    }
    
    static {
        initDefaultLoader();
    }
    
    public static AgentPluginClassLoader getDefault() {
        return DEFAULT_LOADER;
    }
    
    public AgentPluginClassLoader(ClassLoader parent) {
        super(parent);
        classpath = new LinkedList<>();
        classpath.add(new File(PLUGIN_FILE_PATH));
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (allJars.size() == 0) {
            try {
                File file = new File(PLUGIN_FILE_PATH);
                Jar jar = new Jar(new JarFile(file), file);
                allJars.add(jar);
            } catch (IOException ignored) {
            }
        }
        
        String path = name.replace('.', '/').concat(".class");
        for (Jar jar : allJars) {
            JarEntry entry = jar.jarFile.getJarEntry(path);
            if (entry == null) {
                continue;
            }
            try {
                URL classFileUrl = new URL("jar:file:" + jar.sourceFile.getAbsolutePath() + "!/" + path);
                byte[] data;
                try (
                        final BufferedInputStream is = new BufferedInputStream(
                                classFileUrl.openStream());
                        final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    int ch;
                    while ((ch = is.read()) != -1) {
                        baos.write(ch);
                    }
                    data = baos.toByteArray();
                }
                return defineClass(name, data, 0, data.length);
            } catch (IOException ignored) {
            }
        }
        throw new ClassNotFoundException("Can't find " + name);
    }
    
    private static class Jar {
        
        private final JarFile jarFile;
        private final File sourceFile;
        
        public Jar(JarFile jarFile, File sourceFile) {
            this.jarFile = jarFile;
            this.sourceFile = sourceFile;
        }
    }
}
