package org.opengoofy.congomall.springboot.starter.common.threadpool.build;

import org.opengoofy.congomall.springboot.starter.common.toolkit.Assert;
import org.opengoofy.congomall.springboot.starter.design.pattern.builder.Builder;

import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * 线程池 {@link ThreadPoolExecutor} 构建器
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class ThreadPoolBuilder implements Builder<ThreadPoolExecutor> {
    
    private int corePoolSize = calculateCoreNum();
    
    private int maximumPoolSize = corePoolSize + (corePoolSize >> 1);
    
    private long keepAliveTime = 30000L;
    
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    
    private BlockingQueue workQueue = new LinkedBlockingQueue(4096);
    
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
    
    private boolean isDaemon = false;
    
    private String threadNamePrefix;
    
    private ThreadFactory threadFactory;
    
    private Integer calculateCoreNum() {
        int cpuCoreNum = Runtime.getRuntime().availableProcessors();
        return new BigDecimal(cpuCoreNum).divide(new BigDecimal("0.2")).intValue();
    }
    
    public ThreadPoolBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }
    
    public ThreadPoolBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }
    
    public ThreadPoolBuilder maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        if (maximumPoolSize < this.corePoolSize) {
            this.corePoolSize = maximumPoolSize;
        }
        return this;
    }
    
    public ThreadPoolBuilder threadFactory(String threadNamePrefix, Boolean isDaemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.isDaemon = isDaemon;
        return this;
    }
    
    public ThreadPoolBuilder keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }
    
    public ThreadPoolBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        return this;
    }
    
    public ThreadPoolBuilder rejected(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }
    
    public ThreadPoolBuilder workQueue(BlockingQueue workQueue) {
        this.workQueue = workQueue;
        return this;
    }
    
    public static ThreadPoolBuilder builder() {
        return new ThreadPoolBuilder();
    }
    
    @Override
    public ThreadPoolExecutor build() {
        if (threadFactory == null) {
            Assert.notEmpty(threadNamePrefix, "The thread name prefix cannot be empty or an empty string.");
            threadFactory = ThreadFactoryBuilder.builder().prefix(threadNamePrefix).daemon(isDaemon).build();
        }
        ThreadPoolExecutor executorService;
        try {
            executorService = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    timeUnit,
                    workQueue,
                    threadFactory,
                    rejectedExecutionHandler);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Error creating thread pool parameter.", ex);
        }
        return executorService;
    }
}
