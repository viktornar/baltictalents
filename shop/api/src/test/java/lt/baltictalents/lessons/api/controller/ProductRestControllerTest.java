package lt.baltictalents.lessons.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.assertj.core.util.Lists;
import static org.mockito.BDDMockito.given;

import lombok.val;


import lt.baltictalents.lessons.api.model.Image;
import lt.baltictalents.lessons.api.model.Product;
import lt.baltictalents.lessons.api.repository.ProductRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Autowired 
    private ObjectMapper mapper;

    @Before
    public void setup() {
        val image1 = new Image();
        val image2 = new Image();

        image1.setId(1L);
        image1.setName("image 1");
        image1.setUrl("image 1 url");

        image2.setId(2L);
        image2.setName("image 2");
        image2.setUrl("image 2 url");

        val image = new Image();
        image.setName("image to save");
        image.setUrl("image url");

        val product = new Product();
        product.setName("product to save");
        product.setDescription("product to save description");
        product.setPrice(3.54F);
        product.setImages(new HashSet<Image>(Arrays.asList(image)));

        given(productRepository.findAll()).willReturn(Lists.newArrayList(
            new Product(
                1L, "product 1", "product description 1", 2.53F,
                new HashSet<Image>(Arrays.asList(image1, image2))
            ),
            new Product(
                2L, "product 2", "product description 2", 100.34F,
                new HashSet<Image>(Arrays.asList(image1, image2))
            )
        ));

        given(productRepository.save(product)).willReturn(product);
    }


    @Test
    public void testShowProductsResourceList() throws Exception {
        mockMvc.perform(
                get("/api/products")
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(
                content().contentType("application/json;charset=UTF-8")
            )
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].images.length()").value(2))
            .andExpect(jsonPath("$[1].images.length()").value(2));
    }

    @Test
    public void testPostProductsResourceList() throws Exception {
        val image = new Image();
        image.setName("image to save");
        image.setUrl("image url");

        val product = new Product();
        product.setName("product to save");
        product.setDescription("product to save description");
        product.setPrice(3.54F);
        product.setImages(new HashSet<Image>(Arrays.asList(image)));

        String json = mapper.writeValueAsString(product);

        mockMvc.perform(
                post("/api/products/new")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isOk());
    }
}