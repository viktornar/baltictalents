package lt.baltictalents.lesson2;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Enumeration;


public class HttpServletLifecycle extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(HttpServletLifecycle.class.getName());
    private ServletConfig servletConfig;

    public HttpServletLifecycle() {
        super();
        LOGGER.info("Servlet initialised");
    }

    @Override
    public void init(ServletConfig servletConfig) {
        LOGGER.info("Servlet <init> lifecycle");

        this.servletConfig = servletConfig;
        Enumeration parameterNames = servletConfig.getInitParameterNames();

        while (parameterNames.hasMoreElements()) {
            String initParamName = (String)parameterNames.nextElement();
            String initParamValue = servletConfig.getInitParameter(initParamName);
            LOGGER.info(String.format("Servlet get init param: <%s> with init value: <%s>", initParamName, initParamValue));
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Servlet <service> lifecycle as GET method");

        try (PrintWriter out = response.getWriter()) {
            out.println("Service <serivce> lifecycle used for handling GET methods");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("Servlet <service> lifecycle as POST method");

        try (PrintWriter out = response.getWriter()) {
            out.println("Service <serivce> lifecycle used for handling POST methods");
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("Servlet <destroy> lifecycle");
    }


    protected void finalize() throws Throwable {
        LOGGER.info("Servlet is destructed");
        super.finalize();
    }
}
