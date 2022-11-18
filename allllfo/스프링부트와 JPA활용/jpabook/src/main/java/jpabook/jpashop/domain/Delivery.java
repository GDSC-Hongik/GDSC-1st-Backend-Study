package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //Ordinary 가 아닌 string 으로 넣어줘야함 나중에 벼경될 때를 위해서!
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]


}
