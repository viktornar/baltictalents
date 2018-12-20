package lt.baltictalents.lesson4.dao;

import lombok.Getter;
import lombok.Setter;

public class Employee {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String contactNumber;
}
