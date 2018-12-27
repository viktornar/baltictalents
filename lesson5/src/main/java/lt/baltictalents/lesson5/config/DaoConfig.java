package lt.baltictalents.lesson5.config;

import lt.baltictalents.lesson5.dao.DaoHelper;
import lt.baltictalents.lesson5.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {
    @Bean
    DaoHelper daoHelper(DataSource dataSource) {
        return new DaoHelper(dataSource, true);
    }

    @Bean
    UserDao userDao(DaoHelper daoHelper) {
        UserDao userDao = new UserDao();
        userDao.setDaoHelper(daoHelper);
        return userDao;
    }
}
