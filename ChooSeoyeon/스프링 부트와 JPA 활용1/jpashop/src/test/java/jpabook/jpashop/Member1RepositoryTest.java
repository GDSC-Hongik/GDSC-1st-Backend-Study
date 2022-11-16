// 1. 프로젝트 환경설정 - 5) JPA와 DB 설정, 동작확인 - 회원 리포지토리 테스트

package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Member1RepositoryTest {
    @Autowired
    MemberRepository1 memberRepository1;

    // tdd + tab 하여 생성 (Settings -> template 검색 -> live templates -> custom-tdd로 만들어둠)
    @Test
    @Transactional // import springgramework 권장. test끝난 뒤 db Rolled back 함
    @Rollback(false) // test끝난 뒤에도 db에서 결과 보고 싶으면 Rollback을 false로 하면 됨
    public void testMember() throws Exception{
        //given
        Member1 member1 = new Member1();
        member1.setUsername("memberA");

        //when
        Long saveId = memberRepository1.save(member1);
        Member1 findMember1 = memberRepository1.find(saveId);

        //then
        Assertions.assertThat(findMember1.getId()).isEqualTo(member1.getId());
        Assertions.assertThat(findMember1.getUsername()).isEqualTo(member1.getUsername());
        Assertions.assertThat(findMember1).isEqualTo(member1); // 같은 엔티티일까
        System.out.println("findMember == member: " + (findMember1 == member1)); // 같은 trasaction안에선 id값 같으면 같은 엔티티로 식별함
    }
}