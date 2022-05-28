package cn.mall4j.biz.message.application.req;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 邮件发送
 */
@Data
public class MailSendCommand {
    
    /**
     * 标题
     */
    @NotBlank(message = "邮箱标题不能为空")
    private String title;
    
    /**
     * 发送者
     */
    @Email
    @NotBlank(message = "邮箱发送者不能为空")
    private String sender;
    
    /**
     * 接收者
     */
    @Email
    @NotBlank(message = "邮箱接收者不能为空")
    private String receiver;
    
    /**
     * 抄送
     */
    @Email
    private String cc;
    
    /**
     * 消息参数
     */
    private List<String> paramList;
    
    /**
     * 模板ID
     */
    @NotBlank(message = "邮箱模板ID不能为空")
    private String templateId;
}
