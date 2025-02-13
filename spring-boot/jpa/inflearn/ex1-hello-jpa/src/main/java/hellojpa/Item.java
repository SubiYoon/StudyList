package hellojpa;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // ITEM, MOVIE, BOOK, ALBUM 테이블이 생성됨
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // ITEM 테이블하나에 모든 정보 때려넣음
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // MOVIE, BOOk, ALBUM 테이블이 생성
//@DiscriminatorColumn // JOINED, SIGNLE_TABLE시에 필수 삽입
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
