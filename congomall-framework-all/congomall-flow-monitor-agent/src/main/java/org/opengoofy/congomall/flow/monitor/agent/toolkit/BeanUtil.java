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

package org.opengoofy.congomall.flow.monitor.agent.toolkit;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

import static com.github.dozermapper.core.loader.api.TypeMappingOptions.mapEmptyString;
import static com.github.dozermapper.core.loader.api.TypeMappingOptions.mapNull;

/**
 * Bean 工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {
    
    protected static Mapper BEAN_MAPPER_BUILDER;
    
    static {
        BEAN_MAPPER_BUILDER = DozerBeanMapperBuilder.buildDefault();
    }
    
    public static <T, S> T convert(S source, Class<T> clazz) {
        return Optional.ofNullable(source)
                .map(each -> BEAN_MAPPER_BUILDER.map(each, clazz))
                .orElse(null);
    }
    
    public static <T, S> T convert(S source, T target) {
        Optional.ofNullable(source)
                .ifPresent(each -> BEAN_MAPPER_BUILDER.map(each, target));
        return target;
    }
    
    public static <T, S> List<T> convert(List<S> sources, Class<T> clazz) {
        return Optional.ofNullable(sources)
                .map(each -> {
                    List<T> targetList = new ArrayList<T>(each.size());
                    each.forEach(item -> targetList.add(BEAN_MAPPER_BUILDER.map(item, clazz)));
                    return targetList;
                })
                .orElse(null);
    }
    
    public static <T, S> Set<T> convert(Set<S> sources, Class<T> clazz) {
        return Optional.ofNullable(sources)
                .map(each -> {
                    Set<T> targetSize = new HashSet<T>(each.size());
                    each.forEach(item -> targetSize.add(BEAN_MAPPER_BUILDER.map(item, clazz)));
                    return targetSize;
                })
                .orElse(null);
    }
    
    public static void convertIgnoreNullAndBlank(Object source, Object target) {
        DozerBeanMapperBuilder dozerBeanMapperBuilder = DozerBeanMapperBuilder.create();
        Mapper mapper = dozerBeanMapperBuilder.withMappingBuilders(new BeanMappingBuilder() {
            
            @Override
            protected void configure() {
                mapping(source.getClass(), target.getClass(), mapNull(false), mapEmptyString(false));
            }
        }).build();
        mapper.map(source, target);
    }
}
