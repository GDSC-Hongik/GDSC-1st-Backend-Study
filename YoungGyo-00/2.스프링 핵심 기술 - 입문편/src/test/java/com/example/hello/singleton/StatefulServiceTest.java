package com.example.hello.singleton;

import com.example.hello.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);

        int userAPrice = statefulService.order("userA", 10000);
        int userBPrice = statefulService1.order("userB", 20000);

//        int price = statefulService1.getPrice();

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
