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
 * @github https://github.com/opengoofy
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
