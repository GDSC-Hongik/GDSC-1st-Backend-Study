package GDSC.Hongik.hellospring.service;

import GDSC.Hongik.hellospring.domain.Member;
import GDSC.Hongik.hellospring.repository.MemberRepository;
import GDSC.Hongik.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*; // 기본적으로 jUnit Assertions가 들어가있음

// 클래스 네임에서 Ctrl + Shift + T로 테스트 클래스 템플릿을 간단하게 만들 수 있다!

class MemberServiceTest {
    // 선언만 해준다
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트에서 쓰는 MemoryMemberRepository 객체와 MemberService에서 쓰는 객체는 다르다... 문제
    // 지금이야 MemoryMemberRepository의 HashMap이 static으로 선언되어 있어 클래스 영역에 존재하지만...
    // 만약 아니었다면 새로운 데이터베이스가 하나 더 만들어지는 것.
    // 애당초 '다른 것'으로 테스트 하는 것이 좋지는 못함
    // 외부에서 MR을 생성하도록 MemberService를 수정해야 한다.
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @BeforeEach
    public void beforeEach() {
        // MR과 MS를 직접 beforeEach에서 생성한다.
        memberRepository = new MemoryMemberRepository();
        // MS 생성자가 아닌 테스트 클래스에서 MR을 생성하여 MS에 넣어준다
        memberService = new MemberService(memberRepository);
        // MS의 입장에서 MR를 외부에서 넣어주는 것. 이것을 DI (Dependency Injection)이라 한다.
    }
    // 멤버 중복 에러 없애기 위해 클리어 해줘야 함
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트 코드 함수명은 한글로 해도 됨. 빌드 포함 X
        // given - when - then 패턴을 사용하면 편리하다.

        // given - 이런 것들이 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        // when - 이렇게 실행되었을 때
        Long saveId = memberService.join(member);

        // then - 이런 결과가 나와야 함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // Alt+Enter로 static import
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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 람다식을 실행할 때 발생한 예외의 클래스타입을 체크한다.
        // Ctnl+Alt+V로 해당 메서드 리턴값을 할당
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
        // try-catch를 써도 되지만 좀 아쉽...
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}