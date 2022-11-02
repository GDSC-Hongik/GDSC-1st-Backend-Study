package com.example.hello.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
