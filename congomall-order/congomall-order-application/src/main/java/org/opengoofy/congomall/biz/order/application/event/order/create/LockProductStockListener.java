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

package org.opengoofy.congomall.biz.order.application.event.order.create;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.order.infrastructure.remote.ProductStockRemoteService;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductLockStockReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductStockDetailReqDTO;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 锁定商品库存监听
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public final class LockProductStockListener implements ApplicationListener<OrderCreateEvent> {
    
    private final ProductStockRemoteService productStockRemoteService;
    
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        ProductLockStockReqDTO requestParam = ProductLockStockReqDTO.builder()
                .orderSn(event.getOrder().getOrderSn())
                .productStockDetails(BeanUtil.convert(event.getOrder().getOrderProducts(), ProductStockDetailReqDTO.class))
                .build();
        try {
            Result<Boolean> lockProductStockResult = productStockRemoteService.lockProductStock(requestParam);
            if (!lockProductStockResult.isSuccess() || !lockProductStockResult.getData()) {
                throw new ServiceException(lockProductStockResult.getMessage());
            }
        } catch (Throwable ex) {
            log.error("锁定商品库存失败, 入参: {}", JSON.toJSONString(requestParam), ex);
            throw ex;
        }
    }
}
