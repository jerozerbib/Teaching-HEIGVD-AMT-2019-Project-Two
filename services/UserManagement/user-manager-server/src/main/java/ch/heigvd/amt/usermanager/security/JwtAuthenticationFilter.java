package ch.heigvd.amt.usermanager.security;

import ch.heigvd.amt.usermanager.configuration.Myauthtoken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = httpServletRequest.getHeader("authorization");

        try {

            SecurityContext context = SecurityContextHolder.getContext();

            if (authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {
                final String bearerTkn = authenticationHeader.replaceAll("Bearer ", "");


                Jws<Claims> claims = Jwts.parser().requireIssuer("azouari").setSigningKey("amt2019").parseClaimsJws(bearerTkn);
                String user = (String) claims.getBody().get("usr");
                String roles = (String) claims.getBody().get("rol");

                // Creating the list of granted-authorities for the received roles.
                List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
                for (String role : roles.split(","))
                    authority.add(new SimpleGrantedAuthority(role));

                // Creating an authentication object using the claims.
                Myauthtoken authenticationTkn = new Myauthtoken(user, null, authority);
                // Storing the authentication object in the security context.
                context.setAuthentication(authenticationTkn);

            }

            filterChain.doFilter(httpServletRequest,httpServletResponse);
            context.setAuthentication(null);

        } catch (AuthenticationException e) {
            throw new ServletException("Authentication exception");
        }
    }
}
