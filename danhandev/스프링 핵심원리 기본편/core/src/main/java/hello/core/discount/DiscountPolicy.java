package hello.core.discount;

import hello.core.member.Member;

// 할인 정책 인터페이스
public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
