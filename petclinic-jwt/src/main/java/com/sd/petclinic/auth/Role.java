package com.sd.petclinic.auth;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sd.petclinic.model.BaseEntity;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Enumerated(EnumType.STRING)
    @Column(
      name = "name", 
      columnDefinition = "ENUM('ADMIN','EDITOR', 'USER', 'ANONYMOUS')",
      nullable = false
    )
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public void setName(RoleName name) {
      this.name = name;
    }

    public RoleName getName() {
      return name;
    }

    public Set<User> getUsers() {
      return users;
    }

    public void setUsers(Set<User> users) {
      this.users = users;
    }

    public enum RoleName {
        ADMIN,
        EDITOR,
        USER,
        ANONYMOUS
    }
}