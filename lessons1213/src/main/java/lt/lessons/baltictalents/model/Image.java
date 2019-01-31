package lt.lessons.baltictalents.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "images")
@Entity
public class Image extends BaseEntity {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String path;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
}
