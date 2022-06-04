package cn.mall4j.springboot.starter.design.pattern.config;

import cn.mall4j.springboot.starter.base.ApplicationContextHolder;
import cn.mall4j.springboot.starter.design.pattern.strategy.AbstractStrategyChoose;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 设计模式自动装配
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Import(ApplicationContextHolder.class)
public class DesignPatternAutoConfiguration {
    
    /**
     * 策略模式选择器
     */
    @Bean
    public AbstractStrategyChoose abstractStrategyChoose() {
        return new AbstractStrategyChoose();
    }
}
