package cn.mall4j.springboot.starter.design.pattern.strategy;

/**
 * 策略执行抽象
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public interface AbstractExecuteStrategy<T> {
    
    /**
     * 执行策略标识
     *
     * @return
     */
    String mark();
    
    /**
     * 执行策略
     *
     * @param requestParam
     */
    void execute(T requestParam);
}
