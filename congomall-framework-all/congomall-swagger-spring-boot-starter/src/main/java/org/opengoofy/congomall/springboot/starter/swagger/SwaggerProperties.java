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

package org.opengoofy.congomall.springboot.starter.swagger;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置
 */
@Data
@ConfigurationProperties("congomall.swagger")
public class SwaggerProperties {
    
    private Boolean enabled;
    
    private String basePackage = "";
    
    private String title = "";
    
    private String groupName = "";
    
    private String description = "";
    
    private String version = "";
    
    private String host = "";
    
    private String license = "";
    
    private String licenseUrl = "";
    
    private String termsOfServiceUrl = "";
    
    private List<String> basePath = new ArrayList();
    
    private List<String> excludePath = new ArrayList();
    
    private Contact contact = new Contact();
    
    private Authorization authorization = new Authorization();
    
    @Data
    @NoArgsConstructor
    public static class Contact {
        
        private String name = "";
        
        private String url = "";
        
        private String email = "";
    }
    
    @Data
    @NoArgsConstructor
    public static class Authorization {
        
        private String name = "";
        
        private String authRegex = "^.*$";
        
        private List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        
        private List<String> tokenUrlList = new ArrayList<>();
    }
    
    @Data
    @NoArgsConstructor
    public static class AuthorizationScope {
        
        private String scope = "";
        
        private String description = "";
    }
}
