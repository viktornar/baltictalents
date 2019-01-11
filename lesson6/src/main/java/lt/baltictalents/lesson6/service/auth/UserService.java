package lt.baltictalents.lesson6.service.auth;

import lt.baltictalents.lesson6.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}