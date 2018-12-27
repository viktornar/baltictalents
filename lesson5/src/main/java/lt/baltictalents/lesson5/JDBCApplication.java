package lt.baltictalents.lesson5;

import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import lombok.val;
import lt.baltictalents.lesson5.helper.JDBCHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JDBCApplication {
    public static void main(String[] args) {
        val jdbcHelper = new JDBCHelper(
            "root",
            "",
            "mysql",
            "localhost",
            "33060",
            "lesson5"
        );

        try(
            val connection = jdbcHelper.getConnectionDriverManager();
            val stmt = connection.createStatement()
        ) {
            final String CREATE_UPLOAD_FILE_TABLE =
                "CREATE TABLE IF NOT EXISTS UPLOAD_FILE (" +
                    "ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "FILENAME TINYTEXT NOT NULL, " +
                    "CONTENT_TYPE VARCHAR(50) NOT NULL DEFAULT 'IMAGE/JPG', " +
                    "DOWNLOAD_URL TEXT NOT NULL, " +
                    "DESCRIPTION TEXT" +
                ") IF NOT EXIST";

            final String INSERT_UPLOAD_FILE_TABLE =
                "INSERT INTO UPLOAD_FILE " +
                "(FILENAME, DOWNLOAD_URL, DESCRIPTION) " +
                "VALUES ('test', 'some/url', 'some description')";

//            final String UPDATE_UPLOAD_FILE_TABLE = "...";
//            final String DELETE_UPLOAD_FILE_TABLE = "...";
//            final String DROP_UPLOAD_FILE_TABLE = "...";
//            stmt.executeUpdate(CREATE_UPLOAD_FILE_TABLE);
//            stmt.executeUpdate(INSERT_UPLOAD_FILE_TABLE);

            final String SELECT_UPLOAD_FILE_TABLE = "SELECT * FROM UPLOAD_FILE";

            val resultSet = stmt.executeQuery(SELECT_UPLOAD_FILE_TABLE);

            while (resultSet.next()) {
                System.out.println(
                    String.format(
                        "id: %s, filename: %s",
                        resultSet.getInt("ID"),
                        resultSet.getString("FILENAME")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
