package com.example.hello.singleton;

import com.example.hello.AppConfig;
import com.example.hello.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonTest {

    @Test
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println(memberService1);
        System.out.println(memberService2);
    }

    @Test
    void singletonServiceTest() {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService1 = SingletonService.getInstance();

        System.out.println(singletonService);
        System.out.println(singletonService1);

        assertThat(singletonService).isEqualTo(singletonService1);

        singletonService1.logic();
    }

    @Test
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService);

        assertThat(memberService).isEqualTo(memberService1);
    }
}
