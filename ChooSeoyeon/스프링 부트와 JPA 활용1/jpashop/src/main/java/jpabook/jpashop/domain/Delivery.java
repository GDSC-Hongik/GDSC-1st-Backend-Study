// 2. 도메인 분석 설계 - 3) 엔티티 클래스 개발1
// 개체명: 배송
// 속성: 배송id(PK), 주문, 배송주소, 배송상태
// 주인이 아닌 연관관계: [주문]-배송(1:1)

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id; // 배송id(PK)

    @OneToOne(mappedBy = "delivery") // [주문-배송 1:1]관계의 주인 아님. 주문의 delivery 필드에 매핑된 거울일 뿐.
    private Order order; // 주문 (배송id로 해당 배송과 조인한 주문)

    @Embedded
    private Address address; // 배송주소

    @Enumerated(EnumType.STRING) // 기본은 ORDINAL인데, ORDINAL은 숫자 매핑이라 중간에 새로운 타입 생기면 망함. 절대 쓰지 말기.
    private DeliveryStatus status; // 배송상태 [READY, COMP] -> 배송준비, 배송

}
