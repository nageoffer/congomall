package cn.mall4j.biz.message.domain.event;

import cn.mall4j.ddd.framework.core.domain.DomainEvent;
import lombok.Data;

import java.util.Date;

/**
 * 邮箱消息发送事件
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
public class MailMessageSendEvent implements DomainEvent {
    
    /**
     * 消息发送id
     */
    private String messageSendId;
    
    /**
     * 模板id
     */
    private String templateId;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 发送者
     */
    private String sender;
    
    /**
     * 接收者
     */
    private String receiver;
    
    /**
     * 抄送者
     */
    private String cc;
    
    /**
     * 文本参数
     */
    private String paramList;
    
    /**
     * 状态 0：成功 1：失败
     */
    private Integer status;
    
    /**
     * 发送时间
     */
    private Date sendTime;
}
