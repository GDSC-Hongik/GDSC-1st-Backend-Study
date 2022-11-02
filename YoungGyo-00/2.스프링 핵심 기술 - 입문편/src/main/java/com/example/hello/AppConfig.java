package com.example.hello;

import com.example.hello.discount.DiscountPolicy;
import com.example.hello.discount.FixDiscountPolicy;
import com.example.hello.discount.RateDiscountPolicy;
import com.example.hello.member.MemberRepository;
import com.example.hello.member.MemberService;
import com.example.hello.member.MemberServiceImpl;
import com.example.hello.member.MemoryMemberRepository;
import com.example.hello.order.OrderService;
import com.example.hello.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // 이러면 싱글톤이 깨질까?

    @Bean
    public MemberService memberService() {
        System.out.println("MemberServiceImpl");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
