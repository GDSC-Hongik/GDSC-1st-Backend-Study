package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // Member와 pk의 타입 Long을 가진 JpaRepository와 MemberRepository 다중 상속
    // 인터페이스지만, 스프링 데이터 JPA가 SpringDataJpaMemberRepository의 구현체를 만들어서 스프링 빈으로 자동 등록

    // 멤버 엔티티에서 비즈니스마다 필드가 다 다르다... 어떤 곳은 name, 어떤 곳은 username...
    // 이러면 인터페이스로 공통화가 어렵지 않나?
    // 하지만 findBy{field}과 같이 메서드 이름을 지어주면 {field}를 참고하여 쿼리를 짜줌
    // 가령 select m from Member m where m.name과 같은 방식
    // 비슷하게 findByNameAndId와 같이 메서드명을 정하게 되면 name와 id가 일치하는 멤버를 찾는 쿼리를 만듦

    // 이렇듯 스프링 데이터 JPA는 1) 인터페이스를 통한 기본적인 CRUD 2) 메서드 이름만으로 조회 가능 3) 페이징 기능을 제공함
    // 실무에서는 JPA와 스프링 데이터 JPA 기본, 복잡한 동적 쿼리는 Querydsl로 해결
    @Override
    Optional<Member> findByName(String name);
}
