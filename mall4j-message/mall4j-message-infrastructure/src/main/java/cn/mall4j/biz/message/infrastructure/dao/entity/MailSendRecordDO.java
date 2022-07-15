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

package cn.mall4j.biz.message.infrastructure.dao.entity;

import cn.mall4j.mybatisplus.springboot.starter.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 邮件消息发送 DO
 *
 * @author chen.ma
 * @github https://github.com/mabaiwan
 */
@Data
@TableName("mail_send_record")
public class MailSendRecordDO extends BaseDO {
    
    /**
     * id
     */
    private Long id;
    
    /**
     * 消息发送id
     */
    private String messageSendId;
    
    /**
     * 模板id
     */
    private String templateId;
    
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
     * 状态 0：失败 1：成功
     */
    private Integer status;
    
    /**
     * 发送时间
     */
    private Date sendTime;
}
