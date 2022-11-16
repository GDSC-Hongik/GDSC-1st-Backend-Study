// 1. 프로젝트 환경설정 - 5) JPA와 DB 설정, 동작확인 - 회원 리포지토리
// 1.5) 단축키(ctrl+shift+t) 눌러서 test 생성

package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository1 {
    @PersistenceContext
    private EntityManager em;

    public Long save(Member1 member1) {
        em.persist(member1);
        return member1.getId();
    }

    public Member1 find(Long id) {
        return em.find(Member1.class, id);
    }
}
