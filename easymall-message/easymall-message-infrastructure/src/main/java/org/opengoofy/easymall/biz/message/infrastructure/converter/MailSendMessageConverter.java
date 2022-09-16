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

package org.opengoofy.easymall.biz.message.infrastructure.converter;

import org.opengoofy.easymall.biz.message.domain.entity.MessageSend;
import org.opengoofy.easymall.biz.message.infrastructure.dao.entity.MailSendRecordDO;
import com.alibaba.fastjson2.JSON;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * C 端用户 Entity 转换 DO
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Mapper(componentModel = "spring")
public interface MailSendMessageConverter {
    
    /**
     * C 端用户 Entity 转换 DO
     *
     * @param customerUser
     * @return
     */
    @Mapping(target = "paramList", expression = "java(paramListConvert(customerUser.getParamList()))")
    MailSendRecordDO customerUserToDO(MessageSend customerUser);
    
    /**
     * 参数集合转换
     *
     * @param paramList
     * @return
     */
    default String paramListConvert(List<String> paramList) {
        return JSON.toJSONString(paramList);
    }
}
