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

package org.opengoofy.easymall.springboot.starter.convention.page;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页返回对象
 *
 * <p> {@link PageRequest}、{@link PageResponse}
 * 可以理解是防腐层的一种实现，不论底层 ORM 框架，对外分页参数属性不变
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
@Data
public class PageResponse<T> {
    
    /**
     * 当前页
     */
    private Long current;
    
    /**
     * 每页显示条数
     */
    private Long size = 10L;
    
    /**
     * 总数
     */
    private Long total;
    
    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    
    public PageResponse(long current, long size) {
        this(current, size, 0);
    }
    
    public PageResponse(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }
}
