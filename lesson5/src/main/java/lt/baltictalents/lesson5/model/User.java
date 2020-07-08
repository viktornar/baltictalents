package lt.baltictalents.lesson5.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString()
public class User {
    public static final String USERNAME_ADMIN = "admin";

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;

    @Getter
    @Setter
    private String password;


    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private boolean enabled;

    public User() {
    }

    public User(
            String username,
            String password,
            String name,
            String surname,
            String email,
            boolean enabled
    ) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.enabled = enabled;
    }
}
