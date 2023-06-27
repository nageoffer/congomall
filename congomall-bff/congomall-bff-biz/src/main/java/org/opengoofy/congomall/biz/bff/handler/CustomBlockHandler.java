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

package org.opengoofy.congomall.biz.bff.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.opengoofy.congomall.biz.bff.common.ResultT;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.OrderCreateAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ProductCartAddAdapterReqDTO;
import org.opengoofy.congomall.biz.bff.dto.req.adapter.ReceiveAddressSaveAdapterReqDTO;

/**
 * 自定义流控策略
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public class CustomBlockHandler {
    
    public static ResultT<Boolean> addCardBlockHandlerMethod(ProductCartAddAdapterReqDTO requestParam, BlockException exception) {
        return new ResultT().setCode(500).setMessage("当前访问网站人数过多，请稍后再试...");
    }
    
    public static ResultT<Boolean> createOrderBlockHandlerMethod(OrderCreateAdapterReqDTO requestParam, BlockException exception) {
        return new ResultT().setCode(500).setMessage("当前访问网站人数过多，请稍后再试...");
    }
    
    public static ResultT<Boolean> addAddressBlockHandlerMethod(ReceiveAddressSaveAdapterReqDTO requestParam, BlockException exception) {
        return new ResultT().setCode(500).setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
