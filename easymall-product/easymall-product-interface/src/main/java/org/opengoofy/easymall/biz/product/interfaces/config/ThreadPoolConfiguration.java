package org.opengoofy.easymall.biz.product.interfaces.config;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Configuration
public class ThreadPoolConfiguration {
    
    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor productThreadPoolExecutor() {
        String productThreadPoolId = "product-executor";
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolBuilder.builder()
                .threadPoolId(productThreadPoolId)
                .threadFactory(productThreadPoolId)
                .dynamicPool()
                .build();
        return threadPoolExecutor;
    }
}
