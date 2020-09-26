package lt.baltictalents.lesson4.dao;

public class Employee {
    private String name;

    private long id;

    private String contactNumber;

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
