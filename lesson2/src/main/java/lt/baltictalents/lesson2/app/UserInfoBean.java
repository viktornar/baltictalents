package lt.baltictalents.lesson2.app;

public class UserInfoBean {
    private String name = "";
    private String password = "";
    private String gender = "";
    private String age = "";

    private String languages = "";

    UserInfoBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    void setAge(String age) {
        this.age = age;
    }

    public String getLanguages() {
        return languages;
    }

    void setLanguages(String languages) {
        this.languages = languages;
    }
}
