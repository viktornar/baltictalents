package lt.baltictalents.lessons910.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons910.model.Cart;
import lt.baltictalents.lessons910.repository.CartRepository;

@RestController
@RequestMapping("/api")
public class CartRestController {
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/carts")
    List<Cart> getCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @GetMapping("/carts/{id}")
    Cart getCartById(@PathVariable("id") Long id) {
        return cartRepository.findById(id).orElse(new Cart());
    }
}