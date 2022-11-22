// 4. 도메인 분석 설계 - 1) 회원 리포지토리 개발

package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 스프링이 제공. Component scan에 의해 자동으로 스프링 빈으로 관리됨
public class MemberRepository {
    @PersistenceContext // JPA가 제공.
    private EntityManager em; // 스프링이 JPA의 EntityManager 만들어서 주입해줌

    // 저장
    public void save(Member member) {
        em.persist(member); // JPA가 회원 저장함
    }

    // 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id); // JPA가 해당 id의 회원 찾아줌
    }

    // 목록 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // (jpql, 반환타입)
                .getResultList(); // 회원을 리스트로 만들어줌
    }

    // 이름으로 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class) // :name은 parameter 바인딩하는 거임
                .setParameter("name",name)
                .getResultList();
    }
}

