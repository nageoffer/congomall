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

package org.opengoofy.congomall.biz.order.infrastructure.remote;

import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductLockStockReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductUnlockStockReqDTO;
import org.opengoofy.congomall.biz.order.infrastructure.remote.dto.ProductVerifyStockReqDTO;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品库存服务远程调用
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@FeignClient(value = "product-service", url = "${congomall.aggregation.remote-url:}")
public interface ProductStockRemoteService {
    
    /**
     * 验证商品库存
     */
    @PostMapping("/api/product/stock/verify")
    Result<Boolean> verifyProductStock(@RequestBody List<ProductVerifyStockReqDTO> requestParams);
    
    /**
     * 锁定商品库存
     */
    @PutMapping("/api/product/stock/lock")
    Result<Boolean> lockProductStock(@RequestBody ProductLockStockReqDTO requestParam);
    
    /**
     * 解锁商品库存
     */
    @PutMapping("/api/product/stock/unlock")
    Result<Boolean> unlockProductStock(@RequestBody ProductUnlockStockReqDTO requestParam);
}
