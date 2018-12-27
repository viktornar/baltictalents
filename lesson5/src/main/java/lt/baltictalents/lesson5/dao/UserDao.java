package lt.baltictalents.lesson5.dao;

import lt.baltictalents.lesson5.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UserDao extends BaseDao {
    Logger logger = LoggerFactory.getLogger(UserDao.class);

    private static final String USER_COLUMNS = "USERNAME, PASSWORD, NAME, SURNAME, EMAIL, ENABLED";
    private UserRowMapper userRowMapper = new UserRowMapper();

    public User getUserByName(String username) {
        String sql = String.format("SELECT %s FROM USERS WHERE USERNAME=?", USER_COLUMNS);
        return queryOne(sql, userRowMapper, username);
    }

    public List<User> getUsersByQuery(String query) {
        String sql = String.format(
                "SELECT %s FROM USERS WHERE USERNAME LIKE ?" +
                        " OR EMAIL LIKE ?" +
                        " OR NAME LIKE ?" +
                        " OR SURNAME LIKE ?",
                USER_COLUMNS
        );

        String likeQuery = String.format("%%%s%%", query);

        List<User> users = query(sql, userRowMapper, new Object[]{likeQuery, likeQuery, likeQuery, likeQuery});

        return users;
    }

    public User getUserByEmail(String email) {
        String sql = String.format("SELECT %s FROM USERS WHERE EMAIL=?", USER_COLUMNS);
        return queryOne(sql, userRowMapper, email);
    }

    public List<User> getUsersByNames(String... usernames) {
        String sql = String.format("SELECT %s FROM USERS WHERE USERNAME IN (:USERNAME)", USER_COLUMNS);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("USERNAME", Arrays.asList(usernames));
        return getNamedParameterJdbcTemplate().query(sql, parameters, userRowMapper);
    }

    public List<User> getAllUsers() {
        String sql = String.format("SELECT %s FROM USERS", USER_COLUMNS);
        return query(sql, userRowMapper);
    }

    public void createUser(User user) {
        String sql = String.format("INSERT INTO USERS (%s) VALUES (%s)", USER_COLUMNS, questionMarks(USER_COLUMNS));

        update(
                sql,
                StringUtils.lowerCase(user.getUsername(), new Locale("lt", "LT")),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.isEnabled()
        );
    }

    public void deleteUser(String username) {
        if (User.USERNAME_ADMIN.equals(username)) {
            throw new IllegalArgumentException("Can't delete [admin] user.");
        }

        String sql = "DELETE FROM USERS WHERE USERNAME=?";
        update(sql, username);
    }

    public void updateUser(User user) {
        String sql = "UPDATE USERS SET PASSWORD=?, EMAIL=?, ENABLED=?, NAME=?, SURNAME=? WHERE USERNAME=?";
        getJdbcTemplate().update(
                sql,
                user.getPassword(),
                user.getEmail(),
                user.isEnabled(),
                user.getName(),
                user.getSurname(),
                user.getUsername()
        );
    }

    public int getAllUsersCount() {
        String sql = "SELECT COUNT(*) FROM USERS";
        return getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getBoolean(6)
            );
            return user;
        }
    }
}
