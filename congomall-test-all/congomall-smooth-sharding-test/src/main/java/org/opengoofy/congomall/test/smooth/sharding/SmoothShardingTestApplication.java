package org.opengoofy.congomall.test.smooth.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.opengoofy.congomall.test.smooth.sharding.dao.mapper")
public class SmoothShardingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmoothShardingTestApplication.class, args);
    }
}
