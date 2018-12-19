package lt.baltictalents.lesson3.beans.example;

import lt.baltictalents.lesson3.beans.example.dao.AccountDao;
import lt.baltictalents.lesson3.beans.example.dao.AllDao;
import lt.baltictalents.lesson3.beans.example.dao.UserDao;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeansGetter implements ApplicationContextAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public AccountDao getAccountDao() {
        return applicationContext.getBean("accountDao", AccountDao.class);
    }

    public UserDao getUserDao() {
        return applicationContext.getBean("userDao", UserDao.class);
    }

    public AllDao getAllDao() {
        return applicationContext.getBean("allDao", AllDao.class);
    }


    @Bean("accountDaoAnnotated")
    public AccountDao getAccountDaoAnnotated(AccountDao accountDao) {
        return accountDao;
    }
}
