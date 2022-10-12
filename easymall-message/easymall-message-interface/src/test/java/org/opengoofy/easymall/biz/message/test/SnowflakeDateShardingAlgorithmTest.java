package org.opengoofy.easymall.biz.message.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengoofy.easymall.biz.message.infrastructure.dao.entity.MailSendRecordDO;
import org.opengoofy.easymall.biz.message.infrastructure.dao.mapper.MailSendRecordMapper;
import org.opengoofy.easymall.biz.message.web.MessageApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义复合分片算法单元测试
 *
 * @author chen.ma
 * @github https://github.com/itmachen
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageApplication.class)
public class SnowflakeDateShardingAlgorithmTest {
    
    @Resource
    private MailSendRecordMapper mailSendRecordMapper;
    
    @Test
    public void testSendTimeQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .eq(MailSendRecordDO::getSendTime, DateUtil.now());
        executeQuery(queryWrapper);
    }
    
    @Test
    public void testSnowflakeQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .eq(MailSendRecordDO::getMessageSendId, 1547434279292878848L);
        executeQuery(queryWrapper);
    }
    
    @Test
    public void testBetweenQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .between(MailSendRecordDO::getSendTime, DateUtil.parse("2023-07-14 12:12:18"), DateUtil.parse("2024-08-14 12:15:18"));
        executeQuery(queryWrapper);
    }
    
    private void executeQuery(LambdaQueryWrapper queryWrapper) {
        List<MailSendRecordDO> mailSendRecords = mailSendRecordMapper.selectList(queryWrapper);
        log.info("mailSendRecords: {}", JSON.toJSONString(mailSendRecords));
    }
}
