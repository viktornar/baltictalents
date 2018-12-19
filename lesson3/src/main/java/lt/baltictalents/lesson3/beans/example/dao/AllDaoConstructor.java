package lt.baltictalents.lesson3.beans.example.dao;

import lombok.Getter;
import lombok.Setter;

public class AllDaoConstructor {
    @Getter
    @Setter
    public UserDao userDao;

    @Getter
    @Setter
    public AccountDao accountDao;

    public AllDaoConstructor(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }
}
