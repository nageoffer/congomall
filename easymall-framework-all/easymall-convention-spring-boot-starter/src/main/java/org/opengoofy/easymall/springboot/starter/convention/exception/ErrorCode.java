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

package org.opengoofy.easymall.springboot.starter.convention.exception;

/**
 * 基础错误码定义
 *
 * @author chen.ma
 * @github https://github.com/agentart
 */
public enum ErrorCode implements IErrorCode {
    
    // ========== 一级宏观错误码 客户端错误 ==========
    CLIENT_ERROR("A0001", "用户端错误"),
    
    // ========== 二级宏观错误码 用户注册错误 ==========
    USER_REGISTER_ERROR("A0100", "用户注册错误"),
    USER_NAME_VERIFY_ERROR("A0110", "用户名校验失败"),
    USER_NAME_EXIST_ERROR("A0111", "用户名已存在"),
    USER_NAME_SENSITIVE_ERROR("A0112", "用户名包含敏感词"),
    USER_NAME_SPECIAL_CHARACTER_ERROR("A0113", "用户名包含特殊字符"),
    PASSWORD_VERIFY_ERROR("A0120", "密码校验失败"),
    PASSWORD_SHORT_ERROR("A0121", "密码长度不够"),
    PHONE_VERIFY_ERROR("A0151", "手机格式校验失败"),
    
    // ========== 一级宏观错误码 系统执行出错 ==========
    SERVICE_ERROR("B0001", "系统执行出错"),
    // ========== 二级宏观错误码 系统执行超时 ==========
    SERVICE_TIMOUT_ERROR("B0100", "系统执行超时"),
    
    // ========== 一级宏观错误码 调用第三方服务出错 ==========
    REMOTE_ERROR("C0001", "调用第三方服务出错");
    
    private final String code;
    
    private final String message;
    
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    @Override
    public String code() {
        return code;
    }
    
    @Override
    public String message() {
        return message;
    }
}
