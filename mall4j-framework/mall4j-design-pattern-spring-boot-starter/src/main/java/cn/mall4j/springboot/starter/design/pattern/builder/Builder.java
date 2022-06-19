package cn.mall4j.springboot.starter.design.pattern.builder;

import java.io.Serializable;

/**
 * Builder 模式抽象接口
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
public interface Builder<T> extends Serializable {
    
    /**
     * 构建方法
     *
     * @return
     */
    T build();
}
