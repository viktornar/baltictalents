package lt.baltictalents.lesson6.service.auth;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
