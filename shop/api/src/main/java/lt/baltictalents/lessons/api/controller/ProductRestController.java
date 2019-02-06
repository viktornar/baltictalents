package lt.baltictalents.lessons.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons.api.model.Product;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @GetMapping("/products")
    List<Product> getAllProducts() {
        return Arrays.asList(
            new Product(1L, "product 1", "prouct description 1", 2.53F),
            new Product(2L, "product 2", "prouct description 2", 100.34F)
        );
    }
}