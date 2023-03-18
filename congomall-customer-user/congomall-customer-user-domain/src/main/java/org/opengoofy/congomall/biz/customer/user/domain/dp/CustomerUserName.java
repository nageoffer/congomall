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

package org.opengoofy.congomall.biz.customer.user.domain.dp;

import cn.hutool.core.util.StrUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ClientException;
import lombok.Data;

/**
 * C 端用户名称
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
public class CustomerUserName {
    
    /**
     * 用户名
     */
    private final String username;
    
    /**
     * 用户名最少长度
     */
    private final static int MIN_USERNAME_LENGTH = 2;
    
    public CustomerUserName(String username) {
        username = StrUtil.trim(username);
        if (StrUtil.isBlank(username)) {
            throw new ClientException("用户名不允许为空");
        } else if (StrUtil.length(username) < MIN_USERNAME_LENGTH) {
            throw new ClientException("用户名长度最少2位");
        }
        this.username = username;
    }
}
