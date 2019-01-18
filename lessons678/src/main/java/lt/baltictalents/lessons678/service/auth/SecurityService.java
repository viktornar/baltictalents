package lt.baltictalents.lessons678.service.auth;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    String findLoggedInUsername();

    String getCurrentUsername(final HttpServletRequest request);

    void autologin(String username, String password);
}
