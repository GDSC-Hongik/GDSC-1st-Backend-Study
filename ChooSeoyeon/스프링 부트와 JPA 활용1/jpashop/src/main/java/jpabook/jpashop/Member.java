// 1. 프로젝트 환경설정 - 5) JPA와 DB 설정, 동작확인 - 회원 엔티티

package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;

}
