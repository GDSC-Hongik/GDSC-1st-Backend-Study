package hello.spring_core.autowired;

import hello.spring_core.AutoAppConfig;
import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new
                AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000,
                "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        } }
}