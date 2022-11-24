// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 2. 도메인 분석 설계 - 4) 엔티티 클래스 개발2
// 개체명: 상품
// 공통 속성: 상품id(PK), 카테고리list, 이름, 가격, 재고수량
// 추가 속성: 타입(Book, Album, Movie)별 속성
// 주인이 아닌 연관관계: [주문상품]-상품(n:1) -> 사용안해서 list 안가짐
// 공동주인인 연관관계: [상품]-[카테고리](n:n) -> 테이블

package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category; // 2.4
import lombok.Getter; // 2.3
import lombok.Setter; // 2.3

import javax.persistence.*; // 2.3
import java.util.ArrayList; // 2.4
import java.util.List; // 2.4

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item { // 추상클래스 -> 타입(Album, Book, Movie) 표현 위함

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id; // 상품id(PK)

    private String name; // 이름
    private int price; // 가격
    private int stockQuantity; // 재고수량

    // 2.4
    @ManyToMany(mappedBy = "items") // [상품-카테고리 n:n]관계의 공동주인. 카테고리의 items 필드에 매핑된 거울일 뿐.
    private List<Category> categories = new ArrayList<>(); // 카테고리 list (중간테이블로 해당 상품과 조인한 카테고리들)
}