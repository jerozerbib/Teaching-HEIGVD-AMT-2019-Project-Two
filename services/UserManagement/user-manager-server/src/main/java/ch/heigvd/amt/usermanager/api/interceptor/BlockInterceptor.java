package ch.heigvd.amt.usermanager.api.interceptor;


import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.configuration.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BlockInterceptor implements HandlerInterceptor {

    @Autowired
    JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {

        String token = jwtToken.getToken(request);
        int blocked = jwtToken.getIsBlockedFromToken(token);

        if(blocked == 1){
            throw new ApiException(HttpStatus.FORBIDDEN, "User is blocked");
        }

        return true;
    }
}
