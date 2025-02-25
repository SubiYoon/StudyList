package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDTO;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne(ManyToOne, OntToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * 무식하게 엔터티로 조회
     */
    @GetMapping("/api/v1/sample-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName(); // 지연로딩(LAZY) 강제 초기화
//            order.getDelivery().getAddress(); // 지연로딩(LAZY) 강제 초기화
//        }

        return all;
    }

    /**
     * DTO로 변환하하여 조회
     * 성능에 문제가 있음(LAZY로 인한 N + 1 쿼리 문제)
     */
    @GetMapping("/api/v2/sample-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch())
                .stream()
//                .map(SimpleOrderDto::new) // 아래와 코드가 똑같이 동작함
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }

    /**
     * join fetch를 이용한 한방쿼리를 활용
     */
    @GetMapping("/api/v3/sample-orders")
    public List<SimpleOrderDto> ordersV3() {
        return orderRepository.findAllWithMemberDelivery()
                .stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    /**
     * JPA에서 자체적으로 반환형을 DTO로 반환시키는 방법
     * 성능은 좋지만, 다채롭게 활용하기 보단 단순 조회성으로 사용해야할 가능성이 높음
     */
    @GetMapping("/api/v4/sample-orders")
    public List<OrderSimpleQueryDTO> ordersV4() {
        return orderSimpleQueryRepository.findOrderDTOs();
    }

    @Data
    @AllArgsConstructor
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }
}
