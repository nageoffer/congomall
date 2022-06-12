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

package cn.mall4j.biz.customer.user.infrastructure.dao;

import cn.mall4j.mybatisplus.springboot.starter.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * C 端用户操作日志
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@NoArgsConstructor
@TableName("customer_user_operation_log")
public class CustomerOperationLogDO extends BaseDO {
    
    /**
     * 修改前
     */
    private String beforeContent;
    
    /**
     * 修改后
     */
    private String afterContent;
    
    /**
     * 修改内容
     */
    private String operationContent;
    
    public CustomerOperationLogDO(String afterContent) {
        this.afterContent = afterContent;
    }
    
    public CustomerOperationLogDO(String beforeContent, String afterContent, String operationContent) {
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.operationContent = operationContent;
    }
}
