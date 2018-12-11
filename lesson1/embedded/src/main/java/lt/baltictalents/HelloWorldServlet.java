package lt.baltictalents;


import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "helloworld", value = "/")
public class HelloWorldServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");

        if (name == null) {
           name = "world";
        }

        out.println(String.format("Hello, %s!!!", name));
    }
}