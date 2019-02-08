package lt.baltictalents.lessons.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    private Long id;
    private String name;
    private String url;
}