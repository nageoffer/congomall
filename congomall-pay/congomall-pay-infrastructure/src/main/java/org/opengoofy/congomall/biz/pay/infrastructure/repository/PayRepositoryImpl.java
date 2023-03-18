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

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.opengoofy.congomall.biz.pay.domain.aggregate.Pay;
import org.opengoofy.congomall.biz.pay.domain.common.TradeStatusEnum;
import org.opengoofy.congomall.biz.pay.domain.event.PayResultNotifyMessageEvent;
import org.opengoofy.congomall.biz.pay.domain.repository.PayRepository;
import org.opengoofy.congomall.biz.pay.infrastructure.dao.entity.PayDO;
import org.opengoofy.congomall.biz.pay.infrastructure.dao.mapper.PayMapper;
import org.opengoofy.congomall.biz.pay.infrastructure.mq.produce.PayMessageSendProduce;
import org.opengoofy.congomall.springboot.starter.common.toolkit.BeanUtil;
import org.opengoofy.congomall.springboot.starter.convention.exception.ServiceException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 支付聚合根
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PayRepositoryImpl implements PayRepository {
    
    private final PayMapper payMapper;
    
    private final PayMessageSendProduce payMessageSendProduce;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createPay(Pay pay) {
        PayDO insertPay = BeanUtil.convert(pay, PayDO.class);
        insertPay.setStatus(TradeStatusEnum.WAIT_BUYER_PAY.name());
        int result = payMapper.insert(insertPay);
        if (result <= 0) {
            log.error("支付单创建失败，支付聚合根：{}", JSON.toJSONString(pay));
            throw new ServiceException("支付单创建失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void callbackPay(Pay pay) {
        LambdaQueryWrapper<PayDO> queryWrapper = Wrappers.lambdaQuery(PayDO.class)
                .eq(PayDO::getOrderRequestId, pay.getOrderRequestId());
        PayDO payDO = payMapper.selectOne(queryWrapper);
        if (Objects.isNull(payDO)) {
            log.error("支付单不存在，orderRequestId：{}", pay.getOrderRequestId());
            throw new ServiceException("支付单不存在");
        }
        payDO.setTradeNo(pay.getTradeNo());
        payDO.setStatus(pay.getStatus());
        payDO.setPayAmount(pay.getPayAmount());
        payDO.setGmtPayment(pay.getGmtPayment());
        int result = payMapper.updateById(payDO);
        if (result <= 0) {
            log.error("修改支付单支付结果失败，支付单信息：{}", JSON.toJSONString(payDO));
            throw new ServiceException("修改支付单支付结果失败");
        }
        // 交易成功，回调订单服务告知支付结果，修改订单流转状态
        if (Objects.equals(pay.getStatus(), TradeStatusEnum.TRADE_SUCCESS.name())) {
            payMessageSendProduce.payResultNotifyMessageSend(BeanUtil.convert(payDO, PayResultNotifyMessageEvent.class));
        }
    }
}
