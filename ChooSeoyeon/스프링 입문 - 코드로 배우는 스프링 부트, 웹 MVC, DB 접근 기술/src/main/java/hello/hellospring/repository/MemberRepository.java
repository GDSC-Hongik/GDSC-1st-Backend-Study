// 3. 회원 관리 예제 - 백엔드 개발 - 2) 회원 도메인과 리포지토리 만들기
// 회원 리포지토리 인터페이스

package hello.hellospring.repository;

import hello.hellospring.domain.Member; // import(Member)
import java.util.List; // import(List)
import java.util.Optional; // import(Optional)

public interface MemberRepository {
    Member save(Member member); // import(Member) 필요. 회원 저장하면 저장된 회원이 반환됨.
    Optional<Member> findById(Long id); // import(Optional) 필요.
    Optional<Member> findByName(String name);
    List<Member> findAll(); // import(List) 필요.
}
