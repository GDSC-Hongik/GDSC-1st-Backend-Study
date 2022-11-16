package com.example.hello.discount;

import com.example.hello.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
