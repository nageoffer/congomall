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

package org.opengoofy.congomall.biz.customer.user.infrastructure.dao.entity;

import org.opengoofy.congomall.mybatisplus.springboot.starter.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * C 端用户操作日志
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@NoArgsConstructor
@TableName("operation_log")
public class OperationLogDO extends BaseDO {
    
    /**
     * id
     */
    @TableId(type = AUTO)
    private Long id;
    
    /**
     * C端用户ID
     */
    private Long customerUserId;
    
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
    
    public OperationLogDO(String afterContent) {
        this.afterContent = afterContent;
    }
    
    public OperationLogDO(String beforeContent, String afterContent, String operationContent) {
        this.beforeContent = beforeContent;
        this.afterContent = afterContent;
        this.operationContent = operationContent;
    }
}
