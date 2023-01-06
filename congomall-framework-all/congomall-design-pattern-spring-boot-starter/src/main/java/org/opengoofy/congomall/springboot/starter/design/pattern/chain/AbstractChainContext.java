package org.opengoofy.congomall.springboot.starter.design.pattern.chain;

import com.google.common.collect.Maps;
import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 抽象责任链上下文
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public final class AbstractChainContext<T> implements CommandLineRunner {
    
    private final Map<String, List<AbstractChainHandler>> abstractChainHandlerContainer = Maps.newHashMap();
    
    /**
     * 责任链组件执行
     *
     * @param requestParam 请求参数
     */
    public void handler(String mark, T requestParam) {
        abstractChainHandlerContainer.get(mark).stream()
                .sorted(Comparator.comparing(Ordered::getOrder)).forEach(each -> each.handler(requestParam));
    }
    
    @Override
    public void run(String... args) throws Exception {
        Map<String, AbstractChainHandler> chainFilterMap = ApplicationContextHolder.getBeansOfType(AbstractChainHandler.class);
        chainFilterMap.forEach((beanName, bean) -> {
            List<AbstractChainHandler> abstractChainHandlers = abstractChainHandlerContainer.get(bean.mark());
            if (abstractChainHandlers == null) {
                abstractChainHandlers = new ArrayList();
            }
            abstractChainHandlers.add(bean);
            abstractChainHandlerContainer.put(bean.mark(), abstractChainHandlers);
        });
    }
}
