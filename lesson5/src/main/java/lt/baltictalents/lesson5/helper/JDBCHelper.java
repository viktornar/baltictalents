package lt.baltictalents.lesson5.helper;


import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@AllArgsConstructor
public class JDBCHelper {
    @Getter
    @Setter
    public String userName;
    @Getter
    @Setter
    public String password;
    @Getter
    @Setter
    private String dbms;
    @Getter
    @Setter
    private String serverName;
    @Getter
    @Setter
    private String portNumber;
    @Getter
    @Setter
    private String dbName;

    public Connection getConnectionDriverManager() throws SQLException {
        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            connection = DriverManager.getConnection(
                    String.format("jdbc:%s://%s:%s/", this.dbms, this.serverName, this.portNumber),
                    connectionProps
            );
            connection.setCatalog(this.dbName);
        }

        return connection;
    }

    public DataSource getMysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(this.userName);
        dataSource.setPassword(this.password);
        dataSource.setDatabaseName(this.dbName);
        dataSource.setPort(Integer.parseInt(this.portNumber));

        return dataSource;
    }

    public DataSource getConnectionDataSource() throws SQLException {
        return this.getMysqlDataSource();
    }

    public Connection getConnectionToDatabase() throws SQLException {
        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            connection = DriverManager
                    .getConnection(
                            String.format("jdbc:%s://%s:%s/%s", this.dbms, this.serverName, this.portNumber,this.dbName),
                            connectionProps
                    );
            connection.setCatalog(this.dbName);
        }

        System.out.println("Connected to database");

        return connection;
    }

    public static void createDatabase(Connection connection, String dbms, String databaseName) {
        if (dbms.equals("mysql")) {
            try {
                Statement s = connection.createStatement();
                String newDatabaseString = "CREATE DATABASE IF NOT EXISTS " + databaseName;
                s.executeUpdate(newDatabaseString);
                System.out.println("Created database " + databaseName);
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (!ignoreSQLException(((SQLException) e).getSQLState())) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }

    public static boolean ignoreSQLException(String sqlState) {
        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }
        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32"))
            return true;
        // 42Y55: Table already exists in schema
        return sqlState.equalsIgnoreCase("42Y55");
    }
}
