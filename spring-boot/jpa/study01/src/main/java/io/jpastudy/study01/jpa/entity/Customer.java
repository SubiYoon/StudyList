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
// 직접 시퀀스를 생성하는 방법(해당 DB에 시퀀스 생성 기능이 있어야함)
//@SequenceGenerator(
//        name = "customer_generator",
//        sequenceName = "customer_seq",
//        initialValue = 1,
//        allocationSize = 50
//)

// 테이블을 생성하여 해당 테이블의 값을 가져오고 해당 값을 증가시키는 방법(DB와 상관 없음)
@TableGenerator(
        name = "id_generator",
        table = "customer_id",
        pkColumnName = "id_name",
        pkColumnValue = "customer_id",
        valueColumnName = "next_value",
        initialValue = 0,
        allocationSize = 1
)
public class Customer {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    // DB에 있는 시퀀스를 사용하는 방법
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
//    @SequenceGenerator(name = "my_seq", sequenceName = "db_seq")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    private Long id;
    private String name;
    private long registerDate;

    public Customer(String name) {
        this.name = name;
        this.registerDate = System.currentTimeMillis();
    }

    public static Customer sample(){
        return new Customer("Kim");
    }
}
