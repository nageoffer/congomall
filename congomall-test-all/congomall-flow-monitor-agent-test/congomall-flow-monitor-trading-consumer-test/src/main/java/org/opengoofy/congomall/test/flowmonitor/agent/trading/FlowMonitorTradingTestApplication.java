package org.opengoofy.congomall.test.flowmonitor.agent.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients("org.opengoofy.congomall.test.flowmonitor.agent.trading.remote")
@SpringBootApplication
public class FlowMonitorTradingTestApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FlowMonitorTradingTestApplication.class, args);
    }
}
