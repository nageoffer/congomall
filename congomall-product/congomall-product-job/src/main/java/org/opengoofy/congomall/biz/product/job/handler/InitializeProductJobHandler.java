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

package org.opengoofy.congomall.biz.product.job.handler;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.product.infrastructure.dao.entity.ProductSkuDO;
import org.opengoofy.congomall.biz.product.infrastructure.dao.mapper.ProductSkuMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

/**
 * 初始化商品任务，通过并发编程完成生产-消费模型，达到快速同步的效果
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InitializeProductJobHandler extends IJobHandler {
    
    /**
     * 商品 SKU 持久层
     */
    private final ProductSkuMapper productSkuMapper;
    
    /**
     * Hippo4j 线程池，执行同步程序
     */
    private final ThreadPoolExecutor productSkuInitSyncThreadPoolExecutor;
    
    /**
     * 单次同步 ElasticSearch 数量
     */
    private static final Integer MAX_SYNC_SIZE = 5000;
    
    /**
     * 阻塞队列最大容量，相当于一个缓冲池大小
     */
    private static final Integer MAX_POOL_SIZE = 200000;
    
    /**
     * 记录开始时间
     */
    private static Long START_TIME = 0L;
    
    /**
     * 记录同步
     */
    private static final AtomicInteger COUNT_NUM = new AtomicInteger(0);
    
    /**
     * 记录实际同步数量
     */
    private static final LongAdder SYNC_SUM = new LongAdder();
    
    /**
     * 打印输出监控定时器
     */
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newSingleThreadScheduledExecutor();
    
    @XxlJob(value = "demoJobHandler")
    @Override
    public void execute() throws Exception {
        // 定时打印执行进度
        printPoolAndScheduledInfo();
        // 执行商品 SKU 同步程序
        executeProductSkuSync();
        // 释放定时器、同步线程池资源
        shutdownPoolAndPrintCountSize();
    }
    
    void executeProductSkuSync() {
        BlockingQueue<ProductSkuDO> blockingQueueCachePool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        productSkuMapper.listAllProductSkuStreamQuery(resultContext -> {
            // 记录流式查询总数量
            COUNT_NUM.incrementAndGet();
            // 每次向缓冲池添加 MAX_SYNC_SIZE 记录
            try {
                blockingQueueCachePool.put(resultContext.getResultObject());
            } catch (Exception ex) {
                log.error("商品SKU基础数据初始化流程, 添加阻塞队列缓冲池失败, 数据记录: {}",
                        JSON.toJSONString(resultContext.getResultObject()), ex);
            }
            // 避免请求目标数据库（ElasticSearch 或其它）次数过多，所以建议每次 MAX_SYNC_SIZE 条数，虽然可能不够这个数
            if (blockingQueueCachePool.size() >= MAX_SYNC_SIZE) {
                productSkuInitSyncThreadPoolExecutor.execute(() -> executeTask(blockingQueueCachePool));
            }
        });
        // 兜底，将最后缓冲的任务执行
        productSkuInitSyncThreadPoolExecutor.execute(() -> lastOnceExecuteTask(blockingQueueCachePool));
    }
    
    private void executeTask(BlockingQueue<ProductSkuDO> blockingQueueCachePool) {
        List<ProductSkuDO> copyList = new ArrayList<>(MAX_SYNC_SIZE);
        try {
            int drainTo = blockingQueueCachePool.drainTo(copyList, MAX_SYNC_SIZE);
            if (drainTo > 0) {
                // 此处决定向何处同步数据
                // ......
                SYNC_SUM.add(drainTo);
            }
        } catch (Exception ex) {
            log.error("商品SKU基础数据初始化流程执行失败", ex);
        }
    }
    
    private void lastOnceExecuteTask(BlockingQueue<ProductSkuDO> blockingQueueCachePool) {
        List<ProductSkuDO> lastProductSkus = blockingQueueCachePool.stream().parallel().collect(Collectors.toList());
        try {
            SYNC_SUM.add(lastProductSkus.size());
            // 此处决定向何处同步数据
            // ......
        } catch (Exception ex) {
            log.error("商品SKU基础数据初始化流程执行最后一次同步失败", ex);
        }
    }
    
    private void printPoolAndScheduledInfo() {
        START_TIME = System.currentTimeMillis();
        SCHEDULED_EXECUTOR.scheduleAtFixedRate(() -> {
            log.info("商品SKU基础数据初始化流程, 当前已同步总数量: {}", COUNT_NUM.get());
            log.info("商品SKU基础数据初始化流程, 线程池状态打印, 当前活动线程数: {}, 当前排队任务数: {}, 执行完成线程数: {}, 线程池任务总数: {}",
                    productSkuInitSyncThreadPoolExecutor.getActiveCount(),
                    productSkuInitSyncThreadPoolExecutor.getQueue().size(),
                    productSkuInitSyncThreadPoolExecutor.getCompletedTaskCount(),
                    productSkuInitSyncThreadPoolExecutor.getTaskCount());
        }, 30, 10, TimeUnit.SECONDS);
    }
    
    private void shutdownPoolAndPrintCountSize() {
        // 关闭定时器线程池
        SCHEDULED_EXECUTOR.shutdown();
        // 关闭数据同步线程池
        productSkuInitSyncThreadPoolExecutor.shutdown();
        while (true) {
            if (SCHEDULED_EXECUTOR.isTerminated() && productSkuInitSyncThreadPoolExecutor.isTerminated()) {
                log.info("商品SKU基础数据初始化流程, 总条数: {}, 同步成功数: {}, 同步执行总耗时: {}",
                        COUNT_NUM.get(),
                        SYNC_SUM.longValue(),
                        System.currentTimeMillis() - START_TIME);
                break;
            }
        }
    }
}
