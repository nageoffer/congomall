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

package org.opengoofy.congomall.test.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟程序执行抛出 OOM 异常
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class CongoMallOOMTest {
    
    /**
     * 设置 VM 参数：-Xms1m -Xmx1m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:OnOutOfMemoryError=./dev-support/oom.sh
     * <p>
     * -Xms 堆区初始值
     * -Xmx 堆区最大值
     * -XX:+HeapDumpOnOutOfMemoryError 导出堆信息到指定文件，默认关闭
     * -XX:HeapDumpPath 导出堆信息指定路径，配合 -XX:+HeapDumpOnOutOfMemoryError 使用
     * -XX:OnOutOfMemoryError 抛出 OOM 时执行 shell 脚本，可以触发报警或重启应用
     *
     * @param args
     */
    public static void main(String[] args) {
        List<byte[]> oomList = new ArrayList<>();
        while (true) {
            byte[] arr = new byte[10241024];
            oomList.add(arr);
        }
    }
}
