// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1 - 주문 테이블

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id; // 주문id

    @ManyToOne
    @JoinColumn(name="member_id") // FK 이름.
    private Member member; // 회원id(FK)

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>(); // 주문 상품 테이블의 주문id(FK)와 매핑. List로 OrderItem 가짐

    private Delivery delivery; // 배송id(FK)

    private LocalDateTime orderDate; // 주문날짜

    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
