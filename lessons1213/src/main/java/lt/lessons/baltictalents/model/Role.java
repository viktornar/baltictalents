package lt.lessons.baltictalents.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@ToString
public class Role extends BaseEntity {
    @Column(
      name = "name",
      nullable = false
    )
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Getter
    @Setter
    @JsonBackReference
    private Set<User> users;

    public enum RoleName {
        ADMIN,
        EDITOR,
        USER,
        ANONYMOUS
    }
}