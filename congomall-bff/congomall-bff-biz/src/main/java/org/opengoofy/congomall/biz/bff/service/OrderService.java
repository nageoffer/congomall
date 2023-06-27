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

package org.opengoofy.congomall.biz.bff.service;

import org.opengoofy.congomall.biz.bff.dto.req.adapter.OrderCreateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderAdapterRespDTO;
import org.opengoofy.congomall.biz.bff.dto.resp.adapter.OrderResultAdapterRespDTO;

/**
 * 订单接口
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface OrderService {
    
    /**
     * 订单创建
     *
     * @param requestParam 订单创建请求参数
     * @return 订单号
     */
    String addOrder(OrderCreateAdapterReqDTO requestParam);
    
    /**
     * 订单列表查看
     *
     * @param page   当前页
     * @param size   每页多少条
     * @param userId 用户 ID
     * @return 订单列表返回数据
     */
    OrderResultAdapterRespDTO listOrder(Integer page, Integer size, String userId);
    
    /**
     * 根据订单号查询订单详细记录
     *
     * @param orderSn 订单号
     * @return 订单详细返回记录
     */
    OrderAdapterRespDTO getOrderDetail(String orderSn);
    
    /**
     * 根据订单号删除订单
     *
     * @param orderSn 订单号
     * @return 是否删除成功
     */
    Integer deleteOrder(String orderSn);
}
