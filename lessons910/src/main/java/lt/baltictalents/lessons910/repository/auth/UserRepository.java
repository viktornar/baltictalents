package lt.baltictalents.lessons910.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.lessons910.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}