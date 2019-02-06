package lt.baltictalents.lessons.api.model;

import lombok.Data;

@Data
public class Image {
    private Long id;
    private String name;
    private String url;

    public Image(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}