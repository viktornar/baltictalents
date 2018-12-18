package lt.baltictalents.lesson2;


import javax.servlet.*;
// import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

// @WebFilter(urlPatterns={"/*"})
public class RequestTimerFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(RequestTimerFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("RequestTimerFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long before = System.currentTimeMillis();
        chain.doFilter(request, response);
        long after = System.currentTimeMillis();
        String path = ((HttpServletRequest)request).getRequestURI();
        LOGGER.info(String.format("%s: %s msec", path, (after - before)));
    }

    @Override
    public void destroy() {
        LOGGER.info("RequestTimerFilter destroyed");
    }
}
