// 3. 회원 관리 예제 - 백엔드 개발 - 3) 회원 리포지토리 테스트 케이스 작성
// 회원 리포지토리 테스트

package hello.hellospring.repository;

import hello.hellospring.domain.Member; // import Member

import org.assertj.core.api.Assertions; // import Assertions.assertThat
import org.junit.jupiter.api.AfterEach; // import AfterEach
import org.junit.jupiter.api.Test; // import @Test

import java.util.List; // import List
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; // static import Assertiosn.assertThat

public class MemoryMemberRepositoryTest {
    // MemberRepository repository = new MemoryMemberRepository(); // MemoryMemberRepository만 테스트하는 거니까 MemberRepository(인터페이스) -> MemoryMemberRepository로 바꾸기
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // import
    public void afterEach(){ // 테스트들끼리 서로 영향 주고받아서 만듬
        repository.clearStore(); // 테스트가 끝날 때마다 저장소를 다 지움
    }


    @Test // import
    public void save() {
        Member member = new Member(); // import Member 필요. Member클래스의 객체 생성
        member.setName("chooseoyeon");

        repository.save(member); // 리포지토리에 member를 save함. db에 member를 저장함
        Member result = repository.findById(member.getId()).get(); // 제대로 들어갔나 검증하기 위해 db에서 꺼내옴. 반환타입이 optional인데, optional 에서 값을 꺼낼 떈 get으로 꺼낼 수 있음
        // System.out.println("result = " + (result == member)); // 가져온 result가 member랑 같은지 출력
        // Assertions.assertEquals(result, member); // 계속 출력 보긴 어려우니 사용. result 말고 null쓰면 빨간 줄 뜸
        assertThat(member).isEqualTo(result); // import 필요. result말고 null쓰면 빨간 줄 뜸
        // 실무에선 이를 build툴과 엮어서 test단계에서 통과 못하면 다음 단계로 못넘어가게 해버림
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("choodaeyeon");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("choojunyeon");
        repository.save(member2);

        // Optional<Member> result = repository.findByName("choodaeyeon"); // import Optional
        Member result = repository.findByName("choodaeyeon").get(); // get을 쓰면 optional을 한 번 까서 꺼낼 수 있음

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("choodaeyeon");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("choojunyeon");
        repository.save(member2);

        List<Member> result = repository.findAll(); // import List

        assertThat(result.size()).isEqualTo(2);
    }
}


