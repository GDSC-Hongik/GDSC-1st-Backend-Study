package hello.spring_core;

import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;
import hello.spring_core.member.MemoryMemberRepository;
import hello.spring_core.order.OrderService;
import hello.spring_core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
