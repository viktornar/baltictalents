package lt.baltictalents.lessons678.service.auth;

import java.util.List;

import lt.baltictalents.lessons678.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> findAll();
}