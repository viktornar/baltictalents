package com.viktornar.github.petclinic.repositories;

import com.viktornar.github.petclinic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public List<User> findAllByUsername(String username);
    public List<User> findAllByPassword(String password);
    public User findByUsernameAndPassword(String username, String password);

}
