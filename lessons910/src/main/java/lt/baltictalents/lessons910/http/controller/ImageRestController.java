package lt.baltictalents.lessons910.http.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;
import lt.baltictalents.lessons910.model.Image;
import lt.baltictalents.lessons910.repository.ImageRepository;

@RestController
@RequestMapping("/api")
public class ImageRestController {
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/images")
    List<Image> getProducts() {
        var images = imageRepository.findAll();

        if (images == null) {
            images = new ArrayList<Image>();
        }

        return (List<Image>) images;
    }

    @GetMapping("/images/{id}")
    Image getProductById(@PathVariable("id") Long id) {
        return imageRepository.findById(id).orElse(new Image());
    }
}