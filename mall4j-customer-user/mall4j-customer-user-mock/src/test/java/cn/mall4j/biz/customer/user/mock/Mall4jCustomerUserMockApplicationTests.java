package cn.mall4j.biz.customer.user.mock;

import cn.hutool.crypto.digest.MD5;
import cn.mall4j.biz.customer.user.domain.dp.*;
import cn.mall4j.biz.customer.user.domain.entity.CustomerUser;
import cn.mall4j.biz.customer.user.domain.repository.CustomerUserRepository;
import cn.mall4j.springboot.starter.base.ApplicationContextHolder;
import cn.mall4j.springboot.starter.distributedid.SnowflakeIdUtil;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class Mall4jCustomerUserMockApplicationTests {
    
    private final Faker faker = new Faker(Locale.CHINA);
    
    @Test
    public void mockCustomerUserInfo() {
        CustomerUserRepository customerUserRepository = ApplicationContextHolder.getBean(CustomerUserRepository.class);
        CustomerUser customerUser = CustomerUser.builder()
                .customerUserId(SnowflakeIdUtil.nextId())
                .accountNumber(new CustomerUserAccountNumber(faker.number().digits(10)))
                .phone(new CustomerUserPhone(faker.phoneNumber().cellPhone()))
                .password(new CustomerUserPassword(MD5.create().digestHex(faker.number().digits(10))))
                .username(new CustomerUserName(faker.funnyName().name()))
                .mail(new CustomerUserMail(faker.number().digits(10) + "@163.com"))
                .build();
        customerUserRepository.register(customerUser);
    }
}
