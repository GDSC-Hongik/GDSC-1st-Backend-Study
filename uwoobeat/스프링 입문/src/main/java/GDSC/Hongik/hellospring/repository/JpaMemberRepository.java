package GDSC.Hongik.hellospring.repository;

import GDSC.Hongik.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // 데이터베이스 연결 정보 등을 합쳐서 EntityManager로 관리
    // 해당 매니저를 주입받아서 사용
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        // JPA가 setId까지 알아서 해줌
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
        // 엔티티 대상 쿼리. 즉 멤버 객체 자체를 쿼리함
        // inline variable - ctrl + alt + n
    }
}
