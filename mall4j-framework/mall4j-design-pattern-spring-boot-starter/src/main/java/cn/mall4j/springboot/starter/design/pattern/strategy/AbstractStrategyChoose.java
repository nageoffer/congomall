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
