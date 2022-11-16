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
