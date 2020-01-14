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
public class OwnerInterceptor implements HandlerInterceptor {

    @Autowired
    JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ApiException {

        String token = jwtToken.getToken(request);
        int admin = jwtToken.getIsAdminFromToken(token);
        String emailFromToken = jwtToken.getEmailFromToken(token);
        String emailFromPath = request.getServletPath().split("/")[2];

        if(admin != 1 && !emailFromPath.equals(emailFromToken)){
            throw new ApiException(HttpStatus.FORBIDDEN, "You are not authorized to perform action on this user");
        }

        return true;
    }
}
