package hello.spring_core.order;

import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.discount.RateDiscountPolicy;
import hello.spring_core.member.Member;
import hello.spring_core.member.MemberRepository;
import hello.spring_core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 메모리 멤버 리포지토리
    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 코드 변경

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
