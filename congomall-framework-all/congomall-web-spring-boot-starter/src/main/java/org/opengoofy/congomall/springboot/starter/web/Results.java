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

package org.opengoofy.congomall.springboot.starter.web;

import org.opengoofy.congomall.springboot.starter.convention.exception.AbstractException;
import org.opengoofy.congomall.springboot.starter.convention.errorcode.ErrorCode;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;

import java.util.Optional;

/**
 * 全局返回对象构造器
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class Results {
    
    /**
     * 构造成功响应
     *
     * @return
     */
    public static Result<Void> success() {
        return new Result<Void>().setCode(Result.SUCCESS_CODE);
    }
    
    /**
     * 构造带返回数据的成功响应
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode(Result.SUCCESS_CODE).setData(data);
    }
    
    /**
     * 构建服务端失败响应
     *
     * @return
     */
    protected static Result<Void> failure() {
        return new Result<Void>().setCode(ErrorCode.SERVICE_ERROR.code()).setMessage(ErrorCode.SERVICE_ERROR.message());
    }
    
    /**
     * 通过 {@link AbstractException} 构建失败响应
     *
     * @param abstractException
     * @return
     */
    protected static Result<Void> failure(AbstractException abstractException) {
        String errorCode = Optional.ofNullable(abstractException.getErrorCode()).orElse(ErrorCode.SERVICE_ERROR.code());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage()).orElse(ErrorCode.SERVICE_ERROR.message());
        return new Result<Void>().setCode(errorCode).setMessage(errorMessage);
    }
    
    /**
     * 通过 errorCode、errorMessage 构建失败响应
     *
     * @param errorCode
     * @param errorMessage
     * @return
     */
    protected static Result<Void> failure(String errorCode, String errorMessage) {
        return new Result<Void>().setCode(errorCode).setMessage(errorMessage);
    }
}
