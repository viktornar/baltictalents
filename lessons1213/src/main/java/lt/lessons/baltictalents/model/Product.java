package lt.lessons.baltictalents.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="products")
public class Product extends BaseEntity {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Float price;

    @Getter
    @Setter
    @OneToMany(mappedBy="product")
    private List<Image> images;
}
