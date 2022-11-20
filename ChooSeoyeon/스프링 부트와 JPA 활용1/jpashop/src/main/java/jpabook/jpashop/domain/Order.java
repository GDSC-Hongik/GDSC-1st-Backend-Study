// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 주문
// 속성: 주문id(PK), 회원(FK), 배송(FK), 주문상품list, 주문날짜, 주문상태
// 주인인 연관관계: 회원-[주문](1:n), [주문]-배송(1:1)
// 주인이 아닌 연관관계: 주문-[주문상품](1:n)

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*; // Entity, Id, GeneratedValue, Column
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="orders") // 지정안하면 order가 되어버림
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id; // 주문id(PK)

    @ManyToOne(fetch = LAZY) // [회원-주문 1:n]관계의 주인
    @JoinColumn(name="member_id") // 회원id(FK). 회원의 member_id 필드에 매핑
    private Member member; // 회원 (회원id로 조인한 회원)

    @OneToMany(mappedBy = "order") // [주문-주문상품 1:n]관계의 주인 아님. 주문상품의 order 필드에 매핑된 거울일 뿐.
    private List<OrderItem> orderItems = new ArrayList<>(); // 주문상품 list (주문id로 해당 주문과 조인한 주문상품들)

    @OneToOne(fetch = LAZY) // [주문-배송 1:1]관계의 주인
    @JoinColumn(name="delivery_id") // 배송id(FK). 배송의 delivery_id 필드에 매핑
    private Delivery delivery; // 배송 (배송id로 조인한 배송)

    // private Date date; -> 날짜 관리 annotation 매핑해야함
    private LocalDateTime orderDate; // 주문날짜. Hibernate가 알아서 매핑해줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]
}
