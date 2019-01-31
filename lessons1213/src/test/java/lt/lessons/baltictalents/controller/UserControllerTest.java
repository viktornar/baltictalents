package lt.lessons.baltictalents.controller;

import lombok.val;
import lt.lessons.baltictalents.model.Cart;
import lt.lessons.baltictalents.model.Item;
import lt.lessons.baltictalents.model.Role;
import lt.lessons.baltictalents.model.User;
import lt.lessons.baltictalents.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    User user;
    Cart cart;
    Role role;
    Item item;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() {
        role = new Role();
        cart = new Cart();
        item = new Item();
        user = new User();

        role.setName(Role.RoleName.USER.toString());

        item.setDescription("Item description");
        item.setPrice(15.25F);
        item.setName("Item name");

        cart.setItems(Arrays.asList(item));

        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setDecryptedPassword("password");

        val roles = new HashSet<Role>(Arrays.asList(role));
        user.setRoles(roles);

        val carts = new HashSet<Cart>(Arrays.asList(cart));
        user.setCart(carts);
        given(userRepository.findAll()).willReturn(Lists.newArrayList(user));
    }

    @Test
    public void testShowUsersListHtml() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("users"));
    }

    @Test
    public void testShowUsersResourceList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/api/users")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        actions.andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.users[0].name").value("username"));
    }
}