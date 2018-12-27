package lt.baltictalents.lesson5.controller.rest;

import lt.baltictalents.lesson5.dao.UserDao;
import lt.baltictalents.lesson5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDaoRestController {
    @Autowired
    UserDao userDao;

    @GetMapping("/users")
    List<User> getUsers() {
       return userDao.getAllUsers();
    }
}
