package cn.mall4j.springboot.starter.design.pattern.strategy;

import cn.mall4j.springboot.starter.base.ApplicationContextHolder;
import cn.mall4j.springboot.starter.convention.exception.ServiceException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 策略选择器
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public class AbstractStrategyChoose implements ApplicationListener<ApplicationReadyEvent> {
    
    /**
     * 执行策略集合
     */
    private final Map<String, AbstractExecuteStrategy> abstractExecuteStrategyMap = new HashMap<>();
    
    /**
     * 根据 mark 查询具体策略
     *
     * @param mark
     * @return
     */
    public AbstractExecuteStrategy choose(String mark) {
        return Optional.ofNullable(abstractExecuteStrategyMap.get(mark)).orElseThrow(() -> new ServiceException(String.format("%s 策略未定义", mark)));
    }
    
    /**
     * 根据 mark 查询具体策略，并执行
     *
     * @param mark
     * @param requestParam
     * @param <T>
     */
    public <T> void ChooseAndExecute(String mark, T requestParam) {
        AbstractExecuteStrategy executeStrategy = choose(mark);
        executeStrategy.execute(requestParam);
    }
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, AbstractExecuteStrategy> actual = ApplicationContextHolder.getBeansOfType(AbstractExecuteStrategy.class);
        actual.forEach((beanName, bean) -> abstractExecuteStrategyMap.put(bean.mark(), bean));
    }
}
