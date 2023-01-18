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

package org.opengoofy.congomall.biz.pay.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.opengoofy.congomall.biz.pay.domain.aggregate.Pay;
import org.opengoofy.congomall.biz.pay.domain.common.TradeStatusEnum;
import org.opengoofy.congomall.biz.pay.domain.repository.PayRepository;
import org.opengoofy.congomall.biz.pay.infrastructure.dao.entity.PayDO;
import org.opengoofy.congomall.biz.pay.infrastructure.dao.mapper.PayMapper;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付聚合根
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Repository
@RequiredArgsConstructor
public class PayRepositoryImpl implements PayRepository {
    
    private final PayMapper payMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createPay(Pay pay) {
        PayDO insertPay = BeanUtil.convert(pay, PayDO.class);
        insertPay.setStatus(TradeStatusEnum.WAIT_BUYER_PAY.name());
        int result = payMapper.insert(insertPay);
        if (result <= 0) {
            throw new ServiceException("支付单创建失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void callbackPay(Pay pay) {
        LambdaUpdateWrapper<PayDO> updateWrapper = Wrappers.lambdaUpdate(PayDO.class)
                .eq(PayDO::getOrderRequestId, pay.getOrderRequestId());
        PayDO updatePay = new PayDO();
        updatePay.setTradeNo(pay.getTradeNo());
        updatePay.setStatus(pay.getStatus());
        updatePay.setPayAmount(pay.getPayAmount());
        updatePay.setGmtPayment(pay.getGmtPayment());
        int result = payMapper.update(updatePay, updateWrapper);
        if (result <= 0) {
            throw new ServiceException("支付单回调失败");
        }
    }
}
