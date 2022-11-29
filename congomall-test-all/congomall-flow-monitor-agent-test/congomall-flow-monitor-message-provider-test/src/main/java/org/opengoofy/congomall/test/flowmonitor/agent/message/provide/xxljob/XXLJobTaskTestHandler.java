package org.opengoofy.congomall.test.flowmonitor.agent.message.provide.xxljob;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * XXL-Job 任务测试
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@Component
public class XXLJobTaskTestHandler extends IJobHandler {
    
    @XxlJob(value = "demoJobHandler")
    @Override
    public void execute() throws Exception {
        log.info("执行任务...");
    }
}
