package study.jpa.jpa01;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity //DB 테이블과 맵핑 대상
@Table(name = "user") //user 테이블과 맵핑
public class User {
    @Id //식별자에 대응(변수명과 칼럼의 이름이 같으면 다음과 같이 맵핑이 가능)
    private String email; //email 칼럼과 맵핑
    private String name; //name 칼럼과 맵핑

    @Column(name = "create_date") //create_date 칼럼과 맵핑(변수명과 칼럼의 이름이 다르면 해당 칼럼을 명시해서 사용 가능)
    private LocalDateTime createDate;

    public User(String mail, String user, LocalDateTime now) {
        this.email = mail;
        this.name = user;
        this.createDate = now;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public User() {
    }
}
