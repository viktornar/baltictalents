package lt.lessons.baltictalents.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carts")
@ToString
public class Cart extends BaseEntity {
    @Getter
    @Setter
    @OneToMany(mappedBy="cart")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
