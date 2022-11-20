// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 회원
// 속성: 회원id(PK), 주문list, 이름, 주소
// 주인이 아닌 연관관계: 회원-[주문](1:n)

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
    private Long id; // 회원id(PK)

    private String name; // 이름

    @Embedded // 내장타입 포함했단 의미. Embeddable이나 Embedded 둘 중 하나만 있어도 상관없긴함.
    private Address address; // 주소

    @OneToMany(mappedBy = "member") // [회원-주문 1:n]관계의 주인 아님. 주문의 member 필드에 매핑된 거울일 뿐임.
    private List<Order> orders = new ArrayList<>(); // 주문 list (회원id로 해당 회원과 조인한 주문들)
}
