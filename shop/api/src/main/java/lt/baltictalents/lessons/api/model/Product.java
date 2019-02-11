package lt.baltictalents.lessons.api.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
@NoArgsConstructor
public class Product extends BaseEntity {
    private static final long serialVersionUID = 5756914844081469875L;

    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    private Float price;

    @Getter
    @Setter
    @OneToMany(
        cascade=CascadeType.ALL,
        fetch=FetchType.LAZY
    )
    @JoinColumn(name="product_id")
    private Set<Image> images;

    public Product(Long id, String name, String description, Float price, Set<Image> images) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.images = images;
    }
}