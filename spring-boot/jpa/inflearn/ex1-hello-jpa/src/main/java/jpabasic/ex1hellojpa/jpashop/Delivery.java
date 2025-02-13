package jpabasic.ex1hellojpa.jpashop;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

//@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = LAZY, mappedBy = "delivery")
    private Order order;

    public Delivery (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
