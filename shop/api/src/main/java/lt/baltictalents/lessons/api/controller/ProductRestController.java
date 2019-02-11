package lt.baltictalents.lessons.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons.api.exception.ProductNotFoundException;
import lt.baltictalents.lessons.api.model.Product;
import lt.baltictalents.lessons.api.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    Product showProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("/products/new")
    Product showAllProducts(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/edit/{id}")
    Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(newProduct.getName());
                product.setDescription(newProduct.getDescription());
                product.setPrice(newProduct.getPrice());
                product.setImages(newProduct.getImages());
                return productRepository.save(product);
            })
			.orElseGet(() -> {
                newProduct.setId(id);
                return productRepository.save(newProduct);
            });
	}

    @DeleteMapping("/products/{id}")
	void deleteEmployee(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exp) {
            throw new ProductNotFoundException(id);
        }
	}
}