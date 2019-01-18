package lt.baltictalents.lessons678;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lt.baltictalents.lessons678.service.auth.UserService;

@SpringBootApplication
public class Lessons678Application implements CommandLineRunner {
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Lessons678Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Run only once :)
        // val user = new User();
        // val role = new Role();
        // role.setName(Role.RoleName.ADMIN);
        // val roles = new HashSet<Role>();
        // roles.add(role);
        // user.setRoles(roles);
        // user.setUsername("user");
        // user.setPassword("user");
        // userService.save(user);
    }
}
