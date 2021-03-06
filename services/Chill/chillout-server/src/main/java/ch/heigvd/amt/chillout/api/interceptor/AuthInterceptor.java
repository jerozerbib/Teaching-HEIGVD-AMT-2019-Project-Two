package ch.heigvd.amt.chillout.api.interceptor;
/**
     * Handles the Interceptor behavior
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ApiException
     */

import ch.heigvd.amt.chillout.api.exceptions.ApiException;
import ch.heigvd.amt.chillout.api.util.JwtToken;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    JwtToken jwtToken;

    /**
     * Handles the Interceptor behavior
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ApiException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {
        if (request.getMethod().equals("GET") && request.getServletPath().contains("/products")){
            return true;
        }


        String header = request.getHeader("Authorization");
        if(header == null){
            throw new ApiException(HttpStatus.UNAUTHORIZED, "You are not authenticated.");
        }

        if(!header.startsWith("Bearer ")){
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Wrong method of authentication");
        }

        String token = header.split(" ")[1];
        try {
            jwtToken.verify(token);
        }catch (SignatureException signatureException){
            throw new ApiException(HttpStatus.UNAUTHORIZED,"Authentication failed");
        }

        return true;
    }
}