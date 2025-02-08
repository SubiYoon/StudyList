package jpabasic.ex1hellojpa.jpashop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private int stockquantity;

    public Item() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(Long stockquantity) {
        this.stockquantity = stockquantity;
    }
}
