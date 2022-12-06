package org.opengoofy.congomall.test.smooth.sharding.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 分片回滚配置类
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Component
@RefreshScope
@Data
public class ShardRollbackProperties {
    
    @Value("${openShard:false}")
    private Boolean openShard;
}
