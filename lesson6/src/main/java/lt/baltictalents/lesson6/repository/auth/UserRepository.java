package lt.baltictalents.lesson6.repository.auth;

import lt.baltictalents.lesson6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}