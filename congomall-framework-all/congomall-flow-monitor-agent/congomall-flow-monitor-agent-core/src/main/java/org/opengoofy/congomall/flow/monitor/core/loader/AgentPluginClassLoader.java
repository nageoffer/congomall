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

import org.opengoofy.congomall.flow.monitor.core.boot.AgentPackagePath;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 自定义代理插件类加载器
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class AgentPluginClassLoader extends ClassLoader {
    
    private static AgentPluginClassLoader DEFAULT_LOADER;
    private List<File> classpath;
    private List<Jar> allJars;
    private ReentrantLock jarScanLock = new ReentrantLock();
    
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
        File agentDictionary = AgentPackagePath.getPath();
        classpath = new LinkedList<>();
        classpath.add(new File(agentDictionary, "plugins"));
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        List<Jar> allJars = getAllJars();
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
    
    private List<Jar> getAllJars() {
        if (allJars == null) {
            jarScanLock.lock();
            try {
                if (allJars == null) {
                    allJars = new LinkedList<>();
                    for (File path : classpath) {
                        if (path.exists() && path.isDirectory()) {
                            String[] jarFileNames = path.list((dir, name) -> name.endsWith(".jar"));
                            for (String fileName : jarFileNames) {
                                try {
                                    File file = new File(path, fileName);
                                    Jar jar = new Jar(new JarFile(file), file);
                                    allJars.add(jar);
                                } catch (IOException ignored) {
                                }
                            }
                        }
                    }
                }
            } finally {
                jarScanLock.unlock();
            }
        }
        return allJars;
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
