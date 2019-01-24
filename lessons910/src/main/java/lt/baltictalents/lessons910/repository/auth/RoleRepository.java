package lt.baltictalents.lessons910.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.baltictalents.lessons910.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{}