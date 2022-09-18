package org.opengoofy.easymall.rocketmq.springboot.starter.core;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息体包装器
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MessageWrapper<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 唯一标识，可用于幂等性控制
     */
    @NonNull
    private String keys;
    
    /**
     * 消息体
     */
    @NonNull
    private T message;
    
    /**
     * 消息发送时间
     */
    private Date timestamp = new Date();
}
