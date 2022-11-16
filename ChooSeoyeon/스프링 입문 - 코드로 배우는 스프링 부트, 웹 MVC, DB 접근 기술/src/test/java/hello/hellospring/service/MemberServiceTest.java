// 3. 회원 관리 예제 - 백엔드 개발 - 5) 회원 서비스 테스트

package hello.hellospring.service;

import hello.hellospring.domain.Member; // import(Member)
import hello.hellospring.repository.MemoryMemberRepository; // import(MemberMemoryRepository)
import org.assertj.core.api.Assertions; // import(assertj...Assertions)
import org.junit.jupiter.api.AfterEach; // import(AfterEach)
import org.junit.jupiter.api.BeforeEach; // import(BeforeEach)
import org.junit.jupiter.api.Test; // 자동으로 만들어진 것

import static org.assertj.core.api.Assertions.*; // import(assertj...Assertions)의 assertion의 static import
import static org.junit.jupiter.api.Assertions.*; // 자동으로 만들어진 것

class MemberServiceTest {
    // MemberService memberService = new MemberService(); // 변경 전
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //import(MemberMemoryRepository). 변경 전
    // 위의 객체는 MemberService에 있는 memberRepository과는 다른 객체임.
    // 현재 상황에선 MemoryMemberRepository 클래스의 Map이 static으로 선언되어 있어 문제 없지만, static 아니라면 다른 db가 되면서 문제 발생함.

    MemberService memberService; // 변경 후
    MemoryMemberRepository memberRepository; // 변경 후

    @BeforeEach // import(BeforeEach). 동작하기 전에 넣어주기
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // import(AfterEach)
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member(); // import(Member)
        member.setName("spring");

        //when
        Long saveId = memberService.join(member); // store(db)에 member를 저장

        //then
        Member findMember = memberService.findOne(saveId).get(); // store(db)에서 저장된 member 꺼내옴
        assertThat(member.getName()).isEqualTo(findMember.getName()); // import(assertj). assertThat 쓰려면 junit이 아니라 assertj가 필요함
        // member와 db에서 꺼내온 findMember(member)가 같은지 확인
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // IllegalStateException 클래스가 발생해야하고 그 다음에 람다로 넘어감. 예외 터져야함.
        // NullPointerException.class였다면 예외 안터져서 테스트 실패함.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail(); // 예외 안터진 것
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 예외 터져서 정상적으로 성공한 것
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}