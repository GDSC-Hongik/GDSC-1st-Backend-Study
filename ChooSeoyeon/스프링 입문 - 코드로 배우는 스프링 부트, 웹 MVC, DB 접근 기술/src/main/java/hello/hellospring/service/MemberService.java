// 3. 회원 관리 예제 - 백엔드 개발 - 4) 회원 서비스 개발
// 3. 회원 관리 예제 - 백엔드 개발 - 5) 회원 서비스 테스트
// 4.스프링 빈과 의존관계 - 1) 컴포넌트 스캔과 자동 의존관계 설정
// 4.스프링 빈과 의존관계 - 2) 자바코드로 직접 스프링 빈 등록
// 7. AOP - 1) AOP가 필요한 상황
// 7. AOP - 2) AOP 적용 -> 7.1 주석 처리하고 원래대로 되돌리기

package hello.hellospring.service;

import hello.hellospring.domain.Member; // import Member
import hello.hellospring.repository.MemberRepository; // import MemberRepository
import hello.hellospring.repository.MemoryMemberRepository; // import MemoryMemberRepository
import org.springframework.beans.factory.annotation.Autowired; // import 4.1
import org.springframework.stereotype.Service; // import 4.1

import java.util.List; // import List
import java.util.Optional;

//@Service // import 4.1
public class MemberService {
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // 변경 전(3.4)
    private final MemberRepository memberRepository; // import. 리포지토리 생성. 변경 후(3.5)

    //@Autowired // import 4.1
    public MemberService(MemberRepository memberRepository) { // Alt + Insert로 Constructor(생성자)
        this.memberRepository = memberRepository; // memberRepository를 직접 생성하는게 아니라 외부에서 넣어줌
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) { // import.
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
        memberRepository.save(member);
        return member.getId();
// 7.1
//        long start = System.currentTimeMillis(); // 7.1
//
//        try{
//            // 같은 이름이 있는 중복 회원 X
//            validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
//            memberRepository.save(member);
//            return member.getId();
//        } finally { // try finally문 쓰면 finally는 위의 로직 터져도 항상 들어옴 7.1
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 결과가 optional임 -> optionalmember.ifPrest 가능
                .ifPresent( m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); // m에 member가 들어오면(null이 아니면) 이미 존재하는 회원이라고 해줌.
        /* 위 코드는 아래를 줄인 버전임
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){ // import List
        return memberRepository.findAll();
// 7.1
//        long start = System.currentTimeMillis(); // 7.1
//        try{
//            return memberRepository.findAll();
//        }finally{ // 7.1
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
