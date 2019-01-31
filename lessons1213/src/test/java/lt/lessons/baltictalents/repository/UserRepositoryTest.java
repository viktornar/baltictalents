package lt.lessons.baltictalents.repository;


import lombok.val;
import lt.lessons.baltictalents.model.Cart;
import lt.lessons.baltictalents.model.Item;
import lt.lessons.baltictalents.model.Role;
import lt.lessons.baltictalents.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    User user;
    Cart cart;
    Role role;
    Item item;

    @Autowired
    UserRepository userRepository;

    @BeforeClass
    public static void init() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
    }

    @Before
    public void setUp() {
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
        user.setDecryptedPassword("password");
        user.setPasswordConfirm("password");

        val roles = new HashSet<Role>(Arrays.asList(role));
        user.setRoles(roles);

        val carts = new HashSet<Cart>(Arrays.asList(cart));
        user.setCart(carts);
    }

    @Test
    public void saveUserAndFindById() {
        val savedUser = userRepository.save(user);

        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());

        val foundedUser = userRepository.findById(1);
        assertThat(foundedUser.get().getUsername()).isEqualTo(user.getUsername());
    }
}
