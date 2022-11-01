package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRespository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

import java.nio.file.FileStore;

public class AppConfig {
    public MemberService memberService() {
        // 생성자를 통해 구현 객체를 생성하고 주입한다.
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRespository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        // FixDiscountPolicy -> RateDiscountPolicy로 변경해도 구성 영역만 영향 받고,
        // 사용 영역은 영향 받지 않는다.
        return new RateDiscountPolicy();
    }
}
