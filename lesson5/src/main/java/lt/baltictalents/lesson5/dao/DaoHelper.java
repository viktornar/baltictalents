package lt.baltictalents.lesson5.dao;

import javax.sql.DataSource;

import lt.baltictalents.lesson5.migration.schema.Schema;
import lt.baltictalents.lesson5.migration.schema.mysql.MySQLSchema1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


public class DaoHelper {
    Logger logger = LoggerFactory.getLogger(DaoHelper.class);

    private Schema[] schemas = {
        new MySQLSchema1()
    };

    private static boolean shutdownHookAdded;
    private DataSource dataSource;

    public DaoHelper(){

    }

    public DaoHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DaoHelper(DataSource dataSource, boolean dropTablesAndAddShutdownHook) {
        this.dataSource = dataSource;
        if (dropTablesAndAddShutdownHook) {
            dropTables();
            addShutdownHook();
        }
        checkDatabase();
    }

    /**
     * Clean up (drops) all tables while development mode is one after application shutdown
     */
    private void addShutdownHook() {
        if (shutdownHookAdded) {
            return;
        }
        shutdownHookAdded = true;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                dropTables();
            }
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    public void checkDatabase() {
        logger.info("Checking database schema.");
        try {
            for (Schema schema : schemas) {
                schema.execute(getJdbcTemplate());
            }
            logger.info("Done checking database schema.");
        } catch (Exception e) {
            logger.error("Failed to initialize database.");
            e.printStackTrace();
        }
    }

    public void dropTables() {
        logger.info("Dropping tables schema.");
        try {
            for (Schema schema : schemas) {
                schema.drop(getJdbcTemplate());
            }
            logger.info("Done dropping database tables.");
        } catch (Exception e) {
            logger.error("Failed to drop database tables.");
            e.printStackTrace();
        }
    }
}
