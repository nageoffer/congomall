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

package org.opengoofy.congomall.test.smooth.sharding.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.opengoofy.congomall.test.smooth.sharding.dao.entity.PayDO;
import org.opengoofy.congomall.test.smooth.sharding.dao.mapper.PayMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 支付控制层
 *
 * @author chen.ma
 * @github <a href="https://github.com/opengoofy" />
 * @公众号 马丁玩编程，关注回复：资料，领取后端技术专家成长手册
 */
@RestController
@AllArgsConstructor
public class PayController {
    
    private final PayMapper payMapper;
    
    @GetMapping("/pay/{id}")
    public String queryByPayId(@PathVariable("id") String id) {
        LambdaQueryWrapper<PayDO> queryWrapper = Wrappers.lambdaQuery(PayDO.class)
                .between(PayDO::getCreateTime, DateUtil.parse("2022-01-01 00:00:00"), new Date())
                .eq(PayDO::getId, id);
        return JSON.toJSONString(payMapper.selectOne(queryWrapper));
    }
    
    @PostMapping("/pay")
    public String save() {
        PayDO payDO = new PayDO();
        payDO.setId(IdUtil.getSnowflakeNextId());
        payDO.setPayNo(IdUtil.getSnowflakeNextIdStr());
        payMapper.insert(payDO);
        return "success";
    }
}
