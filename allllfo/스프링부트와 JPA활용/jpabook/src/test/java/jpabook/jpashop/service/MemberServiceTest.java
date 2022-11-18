package jpabook.jpashop.service;

import jpabook.jpashop.MemberRepository;
import jpabook.jpashop.domain.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Member;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 회원가입() throws Exception {
    //Given
        Member member = new Member();
        member.setName("kim");
    //When
        Long saveId = memberService.join(member);
    //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
    //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
    //When
        memberService.join(member1); memberService.join(member2); //예외가 발생해야 한다.
    //Then
        fail("예외가 발생해야 한다."); }

}
