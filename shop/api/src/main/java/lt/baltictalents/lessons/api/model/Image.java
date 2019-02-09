package lt.baltictalents.lessons.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="images")
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity {
    private static final long serialVersionUID = -8177925385991595527L;
    
    @Getter
    @Setter
    private String url;

    @ManyToOne(
        fetch=FetchType.LAZY
    )
    private Product product;
}