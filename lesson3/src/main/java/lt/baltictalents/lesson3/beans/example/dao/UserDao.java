package lt.baltictalents.lesson3.beans.example.dao;

import lombok.Getter;
import lombok.Setter;

public class UserDao {
    public UserDao(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Getter
    @Setter
    public String name;

    @Getter
    @Setter
    public String surname;

    @Getter
    @Setter
    public int age;

    @Override
    public String toString() {
        return "This is beans with name userDao";
    }
}
