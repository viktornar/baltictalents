package lt.baltictalents.lessons910.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "carts")
@ToString
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @OneToMany(mappedBy="cart")
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
