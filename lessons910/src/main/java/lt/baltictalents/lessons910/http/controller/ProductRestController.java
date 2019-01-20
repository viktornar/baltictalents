package lt.baltictalents.lessons910.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons910.model.Product;
import lt.baltictalents.lessons910.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    List<Product> getProducts() {
        return (List<Product>)productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable("id") Long id) {
        return productRepository.findById(id).orElse(new Product());
    }
}