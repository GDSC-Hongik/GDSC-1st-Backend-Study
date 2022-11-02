package com.example.hello.autowired;

import com.example.hello.AutoAppConfig;
import com.example.hello.discount.DiscountPolicy;
import com.example.hello.member.Grade;
import com.example.hello.member.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountPrice).isEqualTo(1000);
        assertThat(discountService).isInstanceOf(DiscountService.class);
    }

    // 두 정책 모두 선택을 할 수 있는 경우
    @RequiredArgsConstructor
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println(discountCode);
            System.out.println(discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }


}
