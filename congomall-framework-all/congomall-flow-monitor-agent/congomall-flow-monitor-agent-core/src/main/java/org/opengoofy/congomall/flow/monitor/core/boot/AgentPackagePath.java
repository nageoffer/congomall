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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * AgentPackagePath is a flag and finder to locate the SkyWalking agent.jar. It gets the absolute path of the agent jar.
 * The path is the required metadata for agent core looking up the plugins and toolkit activations. If the lookup
 * mechanism fails, the agent will exit directly.
 */
public final class AgentPackagePath {
    
    private static File AGENT_PACKAGE_PATH;
    
    public static File getPath() throws RuntimeException {
        if (AGENT_PACKAGE_PATH == null) {
            AGENT_PACKAGE_PATH = findPath();
        }
        return AGENT_PACKAGE_PATH;
    }
    
    public static boolean isPathFound() {
        return AGENT_PACKAGE_PATH != null;
    }
    
    private static File findPath() throws RuntimeException {
        String classResourcePath = AgentPackagePath.class.getName().replaceAll("\\.", "/") + ".class";
        
        URL resource = ClassLoader.getSystemClassLoader().getResource(classResourcePath);
        if (resource != null) {
            String urlString = resource.toString();
            
            int insidePathIndex = urlString.indexOf('!');
            boolean isInJar = insidePathIndex > -1;
            
            if (isInJar) {
                urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
                File agentJarFile = null;
                try {
                    agentJarFile = new File(new URL(urlString).toURI());
                } catch (MalformedURLException | URISyntaxException ignored) {
                }
                if (agentJarFile.exists()) {
                    return agentJarFile.getParentFile();
                }
            } else {
                int prefixLength = "file:".length();
                String classLocation = urlString.substring(
                        prefixLength, urlString.length() - classResourcePath.length());
                return new File(classLocation);
            }
        }
        
        throw new RuntimeException("Can not locate agent jar file.");
    }
}
