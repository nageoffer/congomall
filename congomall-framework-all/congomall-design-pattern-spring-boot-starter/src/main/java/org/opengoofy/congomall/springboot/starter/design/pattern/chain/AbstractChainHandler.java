package org.opengoofy.congomall.springboot.starter.design.pattern.chain;

import org.springframework.core.Ordered;

/**
 * 抽象业务责任链组件
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public interface AbstractChainHandler<T> extends Ordered {
    
    /**
     * 执行责任链逻辑
     *
     * @param requestParam 责任链执行入参
     */
    void handler(T requestParam);
    
    /**
     * @return 责任链组件标识
     */
    String mark();
}
