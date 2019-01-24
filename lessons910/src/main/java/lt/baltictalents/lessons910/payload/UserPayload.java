package lt.baltictalents.lessons910.payload;

import java.util.Set;

import lombok.Data;
import lt.baltictalents.lessons910.model.Role;

@Data
public class UserPayload {
  private Long id;
  private String username;
  private Set<Role> roles;
}