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

package org.opengoofy.congomall.test.smooth.sharding.config;

import cn.hutool.core.util.BooleanUtil;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 初始化 Nacos 监听
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Component
public class NacosListenerConfig implements InitializingBean {
    
    @Resource
    private ShardRollbackProperties shardRollbackProperties;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        String serverAddr = "localhost";
        String dataId = "pay-service.properties";
        String group = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener(dataId, group, new Listener() {
            
            @SneakyThrows
            @Override
            public void receiveConfigInfo(String configInfo) {
                Properties properties = new Properties();
                properties.load(new StringReader(configInfo));
                Object openShard = properties.get("openShard");
                shardRollbackProperties.setOpenShard(BooleanUtil.toBoolean(openShard.toString()));
            }
            
            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }
}
