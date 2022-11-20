// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 주문상품
// 속성: 주문상품id(PK), 주문(FK), 상품(FK), 주문금액, 주문수량
// 주인인 연관관계: 주문-[주문상품](1:n), [주문상품]-상품(n:1)

package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*; // Entity, Id, GeneratedValue, Column

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id; // 주문상품id(PK)

    @ManyToOne // [주문상품-상품 n:1]관계의 주인
    @JoinColumn(name="item_id") // 상품id(FK). 상품의 item_id 필드에 매핑
    private Item item; // 상품 (상품id로 조인한 주문)

    @ManyToOne // [주문-주문상품 1:n]관계의 주인
    @JoinColumn(name="order_id") // 주문id(FK). 주문의 order_id 필드에 매핑
    private Order order; // 주문 (주문id로 조인한 주문)

    private int orderPrice; // 주문금액 (주문 당시 가격)

    private int count; // 주문수량 (주문 당시 수량)
}
