package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

//    @NotEmpty // 엔터티에는 Validation을 하지 않고 따로 DTO를 만들어 사용하는게 안정적이다.
    private String name;

    @Embedded
    private Address address;

    @JsonIgnore // 응답할때 해당 데이터는 제외하고 나오게 할 수 있음 하지만 이 역시 DTO를 따로 만들어 사용하는게 안정적이다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}
