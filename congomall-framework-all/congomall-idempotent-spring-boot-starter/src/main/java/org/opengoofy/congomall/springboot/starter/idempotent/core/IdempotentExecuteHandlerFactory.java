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

package org.opengoofy.congomall.springboot.starter.idempotent.core;

import org.opengoofy.congomall.springboot.starter.base.ApplicationContextHolder;
import org.opengoofy.congomall.springboot.starter.idempotent.core.param.IdempotentParamService;
import org.opengoofy.congomall.springboot.starter.idempotent.core.spel.IdempotentSpELByMQExecuteHandler;
import org.opengoofy.congomall.springboot.starter.idempotent.core.spel.IdempotentSpELByRestAPIExecuteHandler;
import org.opengoofy.congomall.springboot.starter.idempotent.core.token.IdempotentTokenService;
import org.opengoofy.congomall.springboot.starter.idempotent.enums.IdempotentSceneEnum;
import org.opengoofy.congomall.springboot.starter.idempotent.enums.IdempotentTypeEnum;

/**
 * 幂等执行处理器工厂
 * <p>
 * Q：可能会有同学有疑问：这里为什么要采用简单工厂模式？策略模式不行么？
 * A：策略模式同样可以达到获取真正幂等处理器功能。但是从设计模式语义来说，简单工厂模式会更合适
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public final class IdempotentExecuteHandlerFactory {
    
    /**
     * 获取幂等执行处理器
     *
     * @param scene 指定幂等验证场景类型
     * @param type  指定幂等处理类型
     * @return 幂等执行处理器
     */
    public static IdempotentExecuteHandler getInstance(IdempotentSceneEnum scene, IdempotentTypeEnum type) {
        IdempotentExecuteHandler result = null;
        switch (scene) {
            case RESTAPI:
                switch (type) {
                    case PARAM:
                        result = ApplicationContextHolder.getBean(IdempotentParamService.class);
                        break;
                    case TOKEN:
                        result = ApplicationContextHolder.getBean(IdempotentTokenService.class);
                        break;
                    case SPEL:
                        result = ApplicationContextHolder.getBean(IdempotentSpELByRestAPIExecuteHandler.class);
                        break;
                    default:
                        break;
                }
                break;
            case MQ:
                result = ApplicationContextHolder.getBean(IdempotentSpELByMQExecuteHandler.class);
                break;
            default:
                break;
        }
        return result;
    }
}
