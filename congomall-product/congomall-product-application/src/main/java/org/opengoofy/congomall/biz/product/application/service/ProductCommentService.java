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

import org.opengoofy.congomall.biz.product.application.req.ProductCommentAppendCommand;
import org.opengoofy.congomall.biz.product.application.req.ProductCommentRemoveCommand;
import org.opengoofy.congomall.biz.product.application.req.ProductCommentSaveCommand;

/**
 * 商品评论
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
public interface ProductCommentService {
    
    /**
     * 新增商品评论
     *
     * @param requestParam 新增商品评论入参
     */
    void saveProductComment(ProductCommentSaveCommand requestParam);
    
    /**
     * 删除商品评论
     *
     * @param requestParam 删除商品评论入参
     */
    void removeProductComment(ProductCommentRemoveCommand requestParam);
    
    /**
     * 追加商品评论
     *
     * @param requestParam 追加商品评论入参
     */
    void appendProductComment(ProductCommentAppendCommand requestParam);
}
