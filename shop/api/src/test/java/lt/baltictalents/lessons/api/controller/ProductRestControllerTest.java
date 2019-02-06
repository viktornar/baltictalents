package lt.baltictalents.lessons.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

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
            .andExpect(jsonPath("$.length()").value(2));
    }
}