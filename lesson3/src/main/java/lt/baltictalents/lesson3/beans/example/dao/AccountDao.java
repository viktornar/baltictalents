package lt.baltictalents.lesson3.beans.example.dao;

import lombok.Getter;
import lombok.Setter;

public class AccountDao {
    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public String surname;

    @Getter
    @Setter
    public String account;

    @Override
    public String toString() {
        return "This is beans with name accountDao";
    }
}
