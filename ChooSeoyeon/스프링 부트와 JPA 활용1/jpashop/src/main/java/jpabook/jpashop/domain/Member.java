// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // PK 이름. 그냥 두면 id라고 그대로 뜸. 우린 member_id로 뜨게 함.
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 연관관계 주인이 아님. 난 Order table의 member field에 매핑된 거울일 뿐이야.
    private List<Order> orders = new ArrayList<>();
}
