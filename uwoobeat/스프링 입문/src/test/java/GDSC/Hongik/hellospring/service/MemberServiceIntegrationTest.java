package GDSC.Hongik.hellospring.service;

import GDSC.Hongik.hellospring.domain.Member;
import GDSC.Hongik.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 클래스 네임에서 Ctrl + Shift + T로 테스트 클래스 템플릿을 간단하게 만들 수 있다!
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행
@Transactional // 테스트 시작 전 트랜잭션을 시작하고, 완료 후 롤백함
class MemberServiceIntegrationTest {
    // 통합 테스트보다 단위 테스트를 잘 만드는 것이 중요
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; // 구현체는 SpringConfig에서 확정

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