package lt.baltictalents.lessons678.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.lessons678.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}