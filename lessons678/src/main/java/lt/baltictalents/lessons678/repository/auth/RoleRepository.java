package lt.baltictalents.lessons678.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.lessons678.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{}