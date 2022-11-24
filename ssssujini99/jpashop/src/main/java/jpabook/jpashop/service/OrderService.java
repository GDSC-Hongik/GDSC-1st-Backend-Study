package jpabook.jpashop.service;


import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문 시 입력정보: --> (주문회원 / 상품명 / 주문수량 )

    // OrderItem (주문상품 생성) --> ( Order / Item, orderPrice, count )

    // Order (주문 생성) --> ( Member / Delivery / orderItems + date , status )


    ////
    // 1. 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 멤버, 아이템 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성 (OrderItem 생성)
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성 (Order 생성)
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    // 2. 취소
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 시 orderId 값만 넘어옴

        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    // 3. 검색
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
