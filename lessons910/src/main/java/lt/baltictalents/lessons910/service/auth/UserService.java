package lt.baltictalents.lessons910.service.auth;

import java.util.List;

import lt.baltictalents.lessons910.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> findAll();
}