package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;

import javax.transaction.Transactional;
import java.lang.reflect.Member;

public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    /** 주문 */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId); Item item = itemRepository.findOne(itemId);
        //배송정보 생성
        Delivery delivery = new Delivery(); delivery.setAddress(member.getAddress()); delivery.setStatus(DeliveryStatus.READY);
        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(),
                count);
        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);  //cascade.all로 해놔서 알아서 다 넘어옴
        //주문 저장
        orderRepository.save(order);
        return order.getId();

    }
    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }
    /** 주문 검색 */
    /*
      public List<Order> findOrders(OrderSearch orderSearch) {
          return orderRepository.findAll(orderSearch);
    } */

}
