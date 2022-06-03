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

package cn.mall4j.springboot.starter.distributedid;

import cn.hutool.core.lang.Snowflake;

/**
 * 分布式雪花 ID 生成器
 *
 * @author chen.ma
 * @github https://github.com/longtai-cn
 */
public class SnowflakeIdUtil {
    
    private static Snowflake SNOWFLAKE;
    
    /**
     * 初始化雪花算法
     *
     * @param snowflake
     */
    public static void initSnowflake(Snowflake snowflake) {
        SnowflakeIdUtil.SNOWFLAKE = snowflake;
    }
    
    /**
     * 获取雪花算法下一个 ID
     *
     * @return
     */
    public static long nextId() {
        return SNOWFLAKE.nextId();
    }
    
    /**
     * 获取雪花算法下一个字符串类型 ID
     *
     * @return
     */
    public static String nextIdStr() {
        return Long.toString(nextId());
    }
}
