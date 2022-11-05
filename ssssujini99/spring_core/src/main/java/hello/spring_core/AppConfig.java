package hello.spring_core;

import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.discount.RateDiscountPolicy;
import hello.spring_core.member.MemberRepository;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;
import hello.spring_core.member.MemoryMemberRepository;
import hello.spring_core.order.OrderService;
import hello.spring_core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository(); // MemberRepository <-- MemoryMemberRepository
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy(); // DiscountPolicy <-- RateDiscountPolicy
    }
}
