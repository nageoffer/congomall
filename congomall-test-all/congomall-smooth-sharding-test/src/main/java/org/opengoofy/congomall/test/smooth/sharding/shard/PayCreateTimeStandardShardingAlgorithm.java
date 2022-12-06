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
 * @github https://github.com/opengoofy
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
        return Lists.newArrayList("pay_info_shard_2022", "pay_info_shard_2023");
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
