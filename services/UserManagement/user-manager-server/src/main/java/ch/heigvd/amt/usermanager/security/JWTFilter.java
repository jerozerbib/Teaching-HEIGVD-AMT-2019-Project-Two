package ch.heigvd.amt.usermanager.security;

import javax.servlet.*;
;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "JWTFilter")
public class JWTFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
    }

}
