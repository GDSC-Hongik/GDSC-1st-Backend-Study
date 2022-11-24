// 4. 도메인 분석 설계 - 2) 회원 서비스 개발

package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        // 중복 회원 검증 로직
        validateDuplicateMember(member); // 문제 발생 시 join 터트림. 문제 없으면 다음 로직으로 넘어감
        memberRepository.save(member);
        return member.getId(); // 항상 값이 있단 게 보장이 됨
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers =
                memberRepository.findByName(member.getName()); // 멀티스레드 같은 상황 고려해서 DB에 name을 unique 제약조건 걸어주는게 좋음
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
