// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; // 주문 당시 가격
    private int count; // 주문 당시 수량
}
