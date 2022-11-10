package hello.spring_introduction.service;

import hello.spring_introduction.domain.Member;
import hello.spring_introduction.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    // MemberService memberService = new MemberService();
    //    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); -> 이거랑 MemberService에 있는 repository랑 두 번 나옴!
    //    이를 MemoryMemberRepository에서 static으로 선언해서 방지했지만, 만일 static이 없다면 db가 두 개 생기는 문제

    // -> 해결책
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 이렇게 직접 넣어줌으로써 -> (이게 DI - Dependency Injection) MemberService에 있는 MemberRepository와 같은 객체 사용!
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); // 테스ㅌ 실행되고 매번 지워주기
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}