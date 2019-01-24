package lt.baltictalents.lessons910.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.baltictalents.lessons910.model.Item;
import lt.baltictalents.lessons910.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemRestController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/items")
    List<Item> getItems() {
        return (List<Item>) itemRepository.findAll();
    }

    @GetMapping("/items/{id}")
    Item getItemById(@PathVariable("id") Long id) {
        return itemRepository.findById(id).orElse(new Item());
    }
}