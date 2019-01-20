package db.migration;

import java.sql.Statement;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.val;

public class V4__user_carts extends BaseJavaMigration {
    private final String INSERT_USER = 
        "insert into users (username, password) values (\"user\", \"%s\")";
    private final String INSERT_ROLE = 
        "insert into roles (name) values (\"USER\")";
    private final String INSERT_USER_ROLE =
        "insert into user_role (user_id, role_id) values (1, 1)";
    private final String ALTER_CARTS_USER_ID = 
        "alter table carts add user_id bigint not null";
    private final String UPDATE_CARTS = 
        "update carts set user_id = 1 where id = 1";
    private final String ALTER_CARTS_FK = 
        "alter table carts add constraint FK_carts_user foreign key (user_id) references users (id)";
    public void migrate(Context context) throws Exception {
        try (Statement statement = context.getConnection().createStatement()) {
            val passwordEncoder = new BCryptPasswordEncoder();
            statement.execute(String.format(INSERT_USER, passwordEncoder.encode("user")));
            statement.execute(String.format(INSERT_ROLE, passwordEncoder.encode("user")));
            statement.execute(String.format(INSERT_USER_ROLE, passwordEncoder.encode("user")));
            statement.execute(ALTER_CARTS_USER_ID);
            statement.execute(UPDATE_CARTS);
            statement.execute(ALTER_CARTS_FK);
        }
    }
}