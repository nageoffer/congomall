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

package org.opengoofy.congomall.biz.bff.remote;

import org.opengoofy.congomall.biz.bff.remote.resp.ProductRespDTO;
import org.opengoofy.congomall.springboot.starter.convention.page.PageResponse;
import org.opengoofy.congomall.springboot.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务远程调用
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@FeignClient(value = "product-service", url = "${congomall.aggregation.remote-url:}")
public interface ProductRemoteService {
    
    /**
     * 根据 SpuID 查询商品详情
     */
    @GetMapping("/api/product/spu/{spuId}")
    Result<ProductRespDTO> getProductBySpuId(@PathVariable("spuId") String spuId);
    
    /**
     * 商品分页查询返回 SPU 信息
     */
    @GetMapping("/api/product/page")
    Result<PageResponse<ProductRespDTO>> pageQueryProduct(@RequestParam("current") Integer page,
                                                          @RequestParam("size") Integer size,
                                                          @RequestParam(value = "sort", required = false) Integer sort,
                                                          @RequestParam(value = "priceGt", required = false) Integer priceGt,
                                                          @RequestParam(value = "priceLte", required = false) Integer priceLte);
}
