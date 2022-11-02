package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRespository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.FileStore;
// 구현 객체는 자신의 로직을 실행하는 역할만 담당하고
// 제어 흐름은 모두 AppConfig가 갖는다.
// -> 이게 프로그램의 제어 흐름을 외부에서 관리하는 IoC 제어의 역전이다.
@Configuration
public class AppConfig {
    // 각 메서드에 @Bean을 붙이면 스프링 컨테이너에 스프링 빈으로 등록된다.
    @Bean
    public MemberService memberService() {
        // 생성자를 통해 구현 객체를 생성하고 주입한다.
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public static MemberRespository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        // FixDiscountPolicy -> RateDiscountPolicy로 변경해도 구성 영역만 영향 받고,
        // 사용 영역은 영향 받지 않는다.
        return new RateDiscountPolicy();
    }
}
