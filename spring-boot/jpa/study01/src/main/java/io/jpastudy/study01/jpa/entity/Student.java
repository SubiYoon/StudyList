package io.jpastudy.study01.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "STUDENT_TB")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String grade;
//    private Long majorId;
    @ManyToOne(fetch = FetchType.LAZY) // get으로 해당 데이터 불러올때 쿼리를 다시 날림
//    @ManyToOne(fetch = FetchType.EAGER) // 데이터를 가져올때 한번에 다 가져옴
    @JoinColumn(name = "MAJORID")
    private Major major;

    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
