// 2. 도메인 분석 설계 - 4) 엔티티 클래스 개발2
// 개체명: 카테고리
// 속성: 카테고리id(PK), 상품list, 이름, 부모, 자식
// 공동주인인 연관관계: [상품]-[카테고리](n:n) -> 테이블

package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id; // 카테고리id(PK)

    private String name; // 이름 (카테고리명)

    @ManyToMany // [상품-카테고리 n:n]관계의 공동주인
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    ) // 중간테이블의 category_id 필드에 매핑 + 중간테이블의 item_id 필드에 매핑
    private List<Item> items = new ArrayList<>(); // 상품 list (중간테이블로 조인한 상품들)

    // 자식 카테고리
    @ManyToOne(fetch = LAZY) // [부모-자식 1:n]관계의 주인
    @JoinColumn(name = "parent_id") // 부모id(FK). 부모의 parent_id 필드에 매핑
    private Category parent; // 부모 (부모id로 조인한 부모)

    // 부모 카테고리
    @OneToMany(mappedBy = "parent") // [부모-자식 1:n]관계의 주인 아님
    private List<Category> child = new ArrayList<>(); // 자식 list (부모id로 해당 부모 조인한 자식들)

}
