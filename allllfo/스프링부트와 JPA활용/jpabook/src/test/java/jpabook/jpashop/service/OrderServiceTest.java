package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import jpabook.jpashop.domain.service.OrderService;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Member;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class OrderServiceTest {
    @RunWith(SpringRunner.class)
    @SpringBootTest
    @Transactional
    public class OrderServiceTest {
        @PersistenceContext
        EntityManager em;
        @Autowired
        OrderService orderService;
        @Autowired
        OrderRepository orderRepository;

        @Test
        public void 상품주문() throws Exception {
            //Given
            Member member = createMember();
            Item item = createBook("시골 JPA", 10000, 10); //이름, 가격, 재고
            int orderCount = 2;
            //When
            Long orderId = orderService.order(member.getId(), item.getId(),
                    orderCount);
            //Then
            Order getOrder = orderRepository.findOne(orderId);
            assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,
                    getOrder.getStatus());
            assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1,
                    getOrder.getOrderItems().size());
            assertEquals("주문 가격은 가격 * 수량이다.", 10000 * 2,
                    getOrder.getTotalPrice());
            assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
        }

        @Test(expected = NotEnoughStockException.class)
        public void 상품주문_재고수량초과() throws Exception { //...
        }

        @Test
        public void 주문취소() {
//...
        }

        private Member createMember() {
            Member member = new Member();
            member.setName("회원1");
            member.setAddress(new Address("서울", "강가", "123-123"));
            em.persist(member);
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setStockQuantity(stockQuantity);
            book.setPrice(price);
            em.persist(book);
            return book;
        }
    }
}

