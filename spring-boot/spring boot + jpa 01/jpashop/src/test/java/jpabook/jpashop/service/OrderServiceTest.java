package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EntityManager em;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception {
        // given
        Member member = createMember();

        Book book = createBook("서울 JPA", 10000, 10);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order order = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, order.getOrderStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, order.getOrderItems().size(), "주문한 상품 종류가 정확해야한다.");
        assertEquals(20000, order.getTotalPrice(), "주문 가격은 가격 * 수량 이어야 한다.");
        assertEquals(8, book.getStockQuantity(), "상품을 주문하면 재고는 감소해야한다.");
    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        // given
        Member member = createMember();

        Book book = createBook("서울 JPA", 10000, 10);

        int orderCount = 11;

        // when
//        orderService.order(member.getId(), book.getId(), orderCount);

        // then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));
    }

    @Test
    void 주문취소() throws Exception {
        // given
        Member member = createMember();
        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancleOrder(order);

        // then
        Order findOrder = orderRepository.findOne(order);

        assertEquals(OrderStatus.CANCEL, findOrder.getOrderStatus(), "주문 취소하면 상태가 CANCLE로 변경되어야 한다.");
        assertEquals(10, book.getStockQuantity(), "주문 취소하면 상태가 CANCLE로 변경되어야 한다.");
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}