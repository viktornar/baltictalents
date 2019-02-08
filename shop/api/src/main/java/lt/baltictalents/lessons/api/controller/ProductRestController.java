package lt.baltictalents.lessons.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.val;
import lt.baltictalents.lessons.api.model.Image;
import lt.baltictalents.lessons.api.model.Product;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @GetMapping("/products")
    List<Product> getAllProducts() {
        val image1 = new Image(1L, "image 1", "image 1 description");
        val image2 = new Image(2L, "image 2", "image 2 description");

        return Arrays.asList(
            new Product(
                1L, "product 1", "product description 1", 2.53F,
                Arrays.asList(image1, image2)
            ),
            new Product(
                2L, "product 2", "product description 2", 100.34F,
                Arrays.asList(image1, image2)
            )
        );
    }
}