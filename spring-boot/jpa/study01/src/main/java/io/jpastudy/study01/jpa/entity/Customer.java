package io.jpastudy.study01.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "customer_tb")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long registerDate;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
        this.registerDate = System.currentTimeMillis();
    }

    public static Customer sample(){
        return new Customer(1L, "Kim");
    }
}
