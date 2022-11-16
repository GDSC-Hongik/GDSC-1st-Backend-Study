package com.example.hello.singleton;

import com.example.hello.AppConfig;
import com.example.hello.member.MemberRepository;
import com.example.hello.member.MemberServiceImpl;
import com.example.hello.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

//        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
//        System.out.println("orderService -> memberRepository  = " + orderService.getMemberRepository());
//        System.out.println("memberRepository = " + memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println(bean);
    }
}
