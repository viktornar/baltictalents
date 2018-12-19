package lt.baltictalents.lesson3.beans.example.dao;

import lombok.Getter;
import lombok.Setter;

public class AllDao {
    @Getter
    @Setter
    public UserDao userDao;

    @Getter
    @Setter
    public AccountDao accountDao;
}
