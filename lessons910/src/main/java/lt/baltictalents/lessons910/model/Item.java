package lt.baltictalents.lessons910.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "items")
@ToString
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String description;

    @Getter
    @Setter
    private Float price;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;
}