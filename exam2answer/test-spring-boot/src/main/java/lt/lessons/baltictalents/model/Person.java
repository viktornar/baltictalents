package lt.lessons.baltictalents.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
class Person extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
