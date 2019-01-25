package lt.baltictalents.answer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('I', 'II', 'III', 'IV')", nullable = false )
    private CourseType course;

    @Getter
    @Setter
    private String lecture;

    public enum CourseType {
        I,
        II,
        III,
        IV
    }
}