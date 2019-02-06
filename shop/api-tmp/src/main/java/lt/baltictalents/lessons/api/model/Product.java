package lt.baltictalents.lessons.api.model;

import java.util.List;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private List<Image> images;

    public Product(Long id, String name, String description, Float price, List<Image> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
    }
}