package cn.mall4j.biz.message.rpc.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 消息发送返回实体
 */
@Data
@AllArgsConstructor
public class MessageSendRespDTO {
    
    /**
     * 消息发送ID
     */
    private String messageSendId;
}
