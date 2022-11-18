package jpabook.jpashop.domain.service;

import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final 있는 필드만 가지고 생성자 만들어줌
public class MemberService {

    private final MemberRepository memberRepository; //필드 인젝션

    /*//@Autowired 생성자 하나만 있는 경우 스프링이 알아서 autowired해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    //회원가입
    public Long join (Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복회원
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다."); }
    }

    //전체회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
