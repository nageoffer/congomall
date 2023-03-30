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

package org.opengoofy.congomall.test.smooth.sharding.shard;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.opengoofy.congomall.test.smooth.sharding.config.ShardRollbackProperties;
import org.slf4j.MDC;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * 支付创建时间分片算法
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class PayCreateTimeStandardShardingAlgorithm implements StandardShardingAlgorithm<Date> {
    
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        if (!getOpenShard() || shardSyncData()) {
            return "pay_info";
        }
        return "pay_info_shard_2022";
    }
    
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        if (!getOpenShard() || shardSyncData()) {
            return Lists.newArrayList("pay_info");
        }
        return Lists.newArrayList("pay_info_shard_2022", "pay_info_shard_2023", "pay_info_shard_2024", "pay_info_shard_2025");
    }
    
    @Override
    public Properties getProps() {
        return null;
    }
    
    @Override
    public void init(Properties properties) {
        
    }
    
    private boolean getOpenShard() {
        ShardRollbackProperties rollbackProperties = ApplicationContextHolder.getBean(ShardRollbackProperties.class);
        return rollbackProperties.getOpenShard();
    }
    
    private boolean shardSyncData() {
        return MDC.get("shard_sync") == null ? false : true;
    }
}
