package lt.baltictalents.lessons678.service.auth;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.val;
import lt.baltictalents.lessons678.model.Role;
import lt.baltictalents.lessons678.model.User;
import lt.baltictalents.lessons678.repository.auth.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getDecryptedPassword()));

        if (user.getRoles() == null) {
            val role = new Role();
            role.setName(Role.RoleName.USER);
            val roles = new HashSet<Role>();
            roles.add(role);
            user.setRoles(roles);
        }

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
	}
}
