package lt.baltictalents.lessons.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons.api.model.Product;
import lt.baltictalents.lessons.api.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}