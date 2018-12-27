package lt.baltictalents.lesson5;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;

public class DatabaseStarter {
    public final static void main(String ...args) throws ManagedProcessException {
        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
        config.setPort(33060);
        config.setDataDir("/Users/viktornareiko/Projects/Baltictalents/mysqldb");
        DB db = DB.newEmbeddedDB(config.build());
        db.start();
    }
}
