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

package cn.mall4j.biz.message.infrastructure.converter;

import cn.mall4j.biz.message.domain.entity.MessageSend;
import cn.mall4j.biz.message.infrastructure.dao.MailSendRecordDO;
import org.mapstruct.Mapper;

/**
 * C 端用户 Entity 转换 DO
 *
 * @author chen.ma
 * @github https://github.com/longtai-cn
 */
@Mapper(componentModel = "spring")
public interface MailSendMessageConverter {
    
    /**
     * C 端用户 Entity 转换 DO
     *
     * @param customerUser
     * @return
     */
    MailSendRecordDO customerUserToDO(MessageSend customerUser);
}
