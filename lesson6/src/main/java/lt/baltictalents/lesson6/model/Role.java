package lt.baltictalents.lesson6.model;

import java.util.Set;

import java.lang.Long;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", columnDefinition = "ENUM('ADMIN','EDITOR', 'READER', 'ANONYMOUS')", nullable = false)
    @Getter
    @Setter
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        EDITOR,
        READER,
        ANONYMOUS
    }
}