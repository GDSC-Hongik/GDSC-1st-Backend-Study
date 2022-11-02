package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // Member와 pk의 타입 Long
    // 스프링 데이터 JPA가 SpringDataJpaMemberRepository의 구현체를 만들어서 스프링 빈으로 자동 등록
    @Override
    Optional<Member> findByName(String name);
}
