package lt.lessons.baltictalents.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
@ToString
public class Item extends BaseEntity {
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Float price;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;
}