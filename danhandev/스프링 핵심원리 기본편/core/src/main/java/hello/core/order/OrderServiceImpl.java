package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRespository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRespository memberRespository;
    //    private final MemberRespository memberRespository = new MemoryMemberRepository();
    // OrderServiceImpl(주문 서비스 클라이언트)는 DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨 듯 보이지만
    // 구체(구현) 클래스인 FixDiscountPolicy도 의존하고 있다. so DIP 위반!
    // FixDiscontPolicy를 RateDiscountPolicy로 변경하고 싶다면 OrderServiceImpl의 소스 코드를 변경해야한다. OCP 위반!
//     private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy;
    @Autowired
    public OrderServiceImpl(MemberRespository memberRespository, DiscountPolicy discountPolicy) {
        this.memberRespository = memberRespository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRespository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRespository getMemberRepository() {
        return memberRespository;
    }
}
