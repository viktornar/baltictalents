package lt.baltictalents.lesson2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet(
        name = "NthServlet",
        value = "/nth-servlet/*"
)
public class NthServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(NthServlet.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String jdbcDriver = getServletContext().getInitParameter("jdbcDriver");
        String databaseUrl = getServletContext().getInitParameter("databaseUrl");
        
        LOGGER.info(String.format("jdbcDriver: <%s>, databaseUrl: <%s>", jdbcDriver, databaseUrl));

        PrintWriter out = response.getWriter();
        out.println(String.format("jdbcDriver: <%s>, databaseUrl: <%s>", jdbcDriver, databaseUrl));
    }
}
