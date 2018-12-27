package lt.baltictalents.lesson5.migration.schema.mysql;

import lombok.NonNull;
import lombok.val;
import lt.baltictalents.lesson5.migration.schema.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

/**
 * Used for creating spring security tables for users registration implementation.
 *
 * @author v.nareiko
 */
public class MySQLSchema1 extends Schema {
    Logger logger = LoggerFactory.getLogger(MySQLSchema1.class);

    @Override
    public void execute(@NonNull JdbcTemplate template) {
        if (!tableExists(template, "VERSION")) {
            logger.info("Database table 'VERSION' not found.  Creating it.");

            template.execute("CREATE TABLE VERSION (VERSION INT NOT NULL)");
            template.execute("INSERT INTO VERSION VALUES (1)");

            logger.info("Database table 'VERSION' was created successfully.");
        }

        if (!tableExists(template, "USERS")) {
            logger.info("Database table 'USERS' not found.  Creating it.");

            template.execute(
                    "CREATE TABLE USERS("
                            + "USERNAME VARCHAR(50) NOT NULL PRIMARY KEY, "
                            + "PASSWORD VARCHAR(50) NOT NULL, "
                            + "NAME VARCHAR(50) NOT NULL, "
                            + "SURNAME VARCHAR(50) NOT NULL, "
                            + "EMAIL VARCHAR(50) NOT NULL, "
                            + "ENABLED BOOLEAN NOT NULL)"
            );

            template.execute(
            "INSERT INTO USERS VALUES (" +
                    "'admin', " +
                    "'admin', " +
                    "'Jonas', " +
                    "'Jonaitis', " +
                    "'admin@localhost', " +
                    "true" +
                ")"
            );

            logger.info("Database table 'USERS' was created successfully.");
        }
    }

    @Override
    public void drop(@NonNull JdbcTemplate template) {
        val tablesToDrop = new ArrayList<String>();

        if (tableExists(template, "USERS")) {
            logger.info("Database table 'USERS' was found.  Adding it to drop list.");
            tablesToDrop.add("USERS");
        }

        drop(template, tablesToDrop);
    }
}
