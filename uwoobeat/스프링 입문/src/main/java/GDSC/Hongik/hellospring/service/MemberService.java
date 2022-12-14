package GDSC.Hongik.hellospring.service;

import GDSC.Hongik.hellospring.domain.Member;
import GDSC.Hongik.hellospring.repository.MemberRepository;
import GDSC.Hongik.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// @Service
// 스프링은 메서드 실행 시 트랙잭션을 시작하고 정상 종료되면 커밋
// 런타임 에러 발생하면 롤백. JPA 통핸 데이터 변경은 트랜잭션 안에서 실행
@Transactional
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MR을 내부에서 직접 new로 생성하는 것이 아니라,
    // 외부로부터 인자를 받아서 멤버 변수에 할당해주는 방식
    // MR이 인스턴스마다 존재하는 문제를 해결하기 위함
    // @Autowired // DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    // DI by setter
//    // 생성자를 사용한 의존성 주입은 생성할 때만 가능하다는 단점이 있다.
//    // 속성을 통한 의존성 주입은 이를 해결할 수 있다.
//    // 하지만 일반적으로 한번 주입된 의존성은 잘 수정되지 않는다. 따라서 생성자 방식이 선호된다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /*
    * 회원가입
    * */
    public Long join(Member member) {
        // 중복 이름 가입 불가
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); -> result를 거치므로 깔끔하지 못함
        validateDuplicateMember(member); // 메서드 분리, 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 하지만 이러한 로직은 별도로 분리하는 것이 좋다
    // Ctrl + Alt + Shift + T로 리팩토링, 여기서 메서드로 추출 가능
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // findByName의 옵셔널 객체를 받아 바로 처리하므로 깔끔
                    throw new IllegalStateException("이미 존재하는 회원입니다."); // 제대로 동작하는지 테스트코드 실행
                });
    }

    // 서비스 클래스는 비즈니스 로직과 가깝게 만들어야. ex) 회원가입, 로그인...
    // 레포지토리 클래스는 개발 로직에 적합하게 만든다

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
