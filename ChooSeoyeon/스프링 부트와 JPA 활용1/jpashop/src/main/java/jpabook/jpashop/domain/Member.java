// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1 - 회원 테이블

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*; // Entity, Id, GeneratedValue, Column
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue // sequence값 써줌
    @Column(name = "member_id") // PK 이름. 그냥 두면 id라고 그대로 뜸. 우린 member_id로 뜨게 함.
    private Long id; // 회원id

    private String name; // 이름

    @Embedded // 내장타입 포함했단 의미. Embeddable이나 Embedded 둘 중 하나만 있어도 상관없긴함.
    private Address address; // 주소

    @OneToMany(mappedBy = "member") // 연관관계 주인이 아님. 난 Order table의 member field에 매핑된 거울일 뿐이야.
    private List<Order> orders = new ArrayList<>(); // 주문 테이블의 회원id(FK)와 매핑. List로 order 가짐
}
