package lt.baltictalents.lesson2.app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@WebServlet(name="UserInputServlet", value="/user-input/*")
public class UserInputServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(UserInputServlet.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user-input.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String languages = String.join(", ", Arrays.asList(request.getParameterValues("language")));

        LOGGER.info(String.format("%s %s %s %s %s", username, password, gender, age, languages));

        UserInfoBean userInfoBean = new UserInfoBean();

        userInfoBean.setName(username);
        userInfoBean.setPassword(password);
        userInfoBean.setAge(age);
        userInfoBean.setGender(gender);
        userInfoBean.setLanguages(languages);

        request.setAttribute("userInfoBean", userInfoBean);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user-info.jsp");
        requestDispatcher.forward(request, response);
    }
}
