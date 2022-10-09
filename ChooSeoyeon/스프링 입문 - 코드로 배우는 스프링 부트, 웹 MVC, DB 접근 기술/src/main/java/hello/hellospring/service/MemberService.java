// 3. 회원 관리 예제 - 백엔드 개발 - 4) 회원 서비스 개발

package hello.hellospring.service;

import hello.hellospring.domain.Member; // import Member
import hello.hellospring.repository.MemberRepository; // import MemberRepository
import hello.hellospring.repository.MemoryMemberRepository; // import MemoryMemberRepository

import java.util.List; // import List
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // import. 리포지토리 생성

    /**
     * 회원 가입
     */
    public Long join(Member member) { // import.
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // extract method 사용했음(ctrl+alt+shift+t)
        memberRepository.save(member);
        return member.getId();
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
    private List<Member> findMembers(){ // import List
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
