package ch.heigvd.amt.usermanager.api.interceptor;


import ch.heigvd.amt.usermanager.api.exceptions.ApiException;
import ch.heigvd.amt.usermanager.api.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {

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

        String token = jwtToken.getToken(request);
        String method = request.getMethod();
        int admin = jwtToken.getIsAdminFromToken(token);

        if ((method.equals("POST") || method.equals("GET"))  && admin != 1){
            throw new ApiException(HttpStatus.FORBIDDEN, "Only an admin can create/list users." );
        }
        return true;
    }
}
