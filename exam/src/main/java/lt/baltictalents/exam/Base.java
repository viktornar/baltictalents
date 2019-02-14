package lt.baltictalents.exam;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="bases")
@ToString
public class Base {
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Getter
    @Setter
    private String name;

    @Transient
    public boolean isNew() {
        return this.id == null;
    }
}