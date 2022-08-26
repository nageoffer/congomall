package cn.mall4j.biz.customer.user.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding({Source.class, Sink.class})
@EnableFeignClients("cn.mall4j.biz.customer.user.infrastructure.remote")
@SpringBootApplication(scanBasePackages = "cn.mall4j.biz.customer.user")
@MapperScan("cn.mall4j.biz.customer.user.infrastructure.dao")
public class Mall4jCustomerUserMockApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(Mall4jCustomerUserMockApplication.class, args);
    }
}
