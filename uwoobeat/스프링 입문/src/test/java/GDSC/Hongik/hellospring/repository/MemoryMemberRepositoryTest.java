package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    // class에서 돌리면 모든 테스트를 확인해볼 수 있다
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트 메소드 종료 후 실행되는 메서드
    public void afterEach() {
        repository.clearStore(); // 실행될 때마다 레포지토리를 지워준다
        // 중복되는 멤버가 없어지므로, 더 이상 에러 발생하지 않음
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional에서 값 받아오려면 get
        System.out.println("result = " + (result == member));
        //  assertEquals(member, result); member와 result가 같은지 확인
        // AssertJ - 다양한 assertions를 통해 에러 메세지와 코드 가독성에 도움을 줌!
        assertThat(member).isEqualTo(result); // member를 넣었을 때 - result와 동일하면
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        // shift + F6으로 일괄 리네임 가능

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
        // 에러를 직관적으로 확인할 수 있다.
    }
}
