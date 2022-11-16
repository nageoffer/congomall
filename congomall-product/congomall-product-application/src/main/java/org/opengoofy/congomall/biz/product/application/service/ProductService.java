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

package org.opengoofy.congomall.biz.product.application.service;

import org.opengoofy.congomall.biz.product.application.resp.ProductRespDTO;

/**
 * 商品服务
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
public interface ProductService {
    
    /**
     * 根据 spuId 查询商品信息
     *
     * @param spuId
     * @return
     */
    ProductRespDTO getProductBySpuId(Long spuId);
}
