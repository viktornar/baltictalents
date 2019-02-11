package lt.baltictalents.lessons.api.seeder;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.val;
import lt.baltictalents.lessons.api.model.Image;
import lt.baltictalents.lessons.api.model.Product;
import lt.baltictalents.lessons.api.repository.ProductRepository;

@Component
public class DatabaseSeeder {
    @Autowired
    ProductRepository productRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedProductsImagesTable();
    }

    private void seedProductsImagesTable() {
        if (productRepository.count() == 0) {
            val image1 = new Image();
            val image2 = new Image();
            val image3 = new Image();
            val image4 = new Image();
            val image5 = new Image();
            val image6 = new Image();

            image1.setName("image 1");
            image1.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            image2.setName("image 2");
            image2.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            image3.setName("image 3");
            image3.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            image4.setName("image 4");
            image4.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            image5.setName("image 5");
            image5.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            image6.setName("image 6");
            image6.setUrl("https://dummyimage.com/600x400/b9bf88/1a171a.png");

            val product1 = new Product();
            val product2 = new Product();
            val product3 = new Product();

            product1.setName("product 1");
            product1.setDescription("product 1 description");
            product1.setPrice(2.75F);
            product1.setImages(new HashSet<Image>(Arrays.asList(image1, image2)));

            product2.setName("product 2");
            product2.setDescription("product 2 description");
            product2.setPrice(3.75F);
            product2.setImages(new HashSet<Image>(Arrays.asList(image3, image4)));

            product3.setName("product 3");
            product3.setDescription("product 3 description");
            product3.setPrice(4.75F);
            product3.setImages(new HashSet<Image>(Arrays.asList(image5, image6)));

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
        }
    }
}