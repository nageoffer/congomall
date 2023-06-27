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

package org.opengoofy.congomall.biz.bff.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * 对接商城前端 BFF 层返回对象
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Data
@Accessors(chain = true)
public class ResultT<T> implements Serializable {
    
    private static final long serialVersionUID = 5679018624309023727L;
    
    /**
     * 正确返回码
     */
    public static final int SUCCESS_CODE = 200;
    
    /**
     * 正确返回信息
     */
    public static final String SUCCESS_MESSAGE = "success";
    
    /**
     * 返回码
     */
    private int code;
    
    /**
     * 返回消息
     */
    private String message;
    
    /**
     * 响应数据
     */
    private T result;
    
    /**
     * 当前返回时间戳
     */
    private long timestamp = System.currentTimeMillis();
    
    public boolean isSuccess() {
        return Objects.equals(SUCCESS_CODE, code);
    }
    
    public static <T> ResultT<T> success() {
        return new ResultT().setCode(SUCCESS_CODE).setMessage(SUCCESS_MESSAGE);
    }
    
    public static <T> ResultT<T> success(T data) {
        return new ResultT().setCode(SUCCESS_CODE).setMessage(SUCCESS_MESSAGE).setResult(data);
    }
}