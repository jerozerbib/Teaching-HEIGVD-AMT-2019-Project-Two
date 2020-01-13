package ch.heigvd.amt.usermanager.configuration;

import ch.heigvd.amt.usermanager.api.interceptor.AdminInterceptor;
import ch.heigvd.amt.usermanager.api.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig  implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/users","/users/*").order(1);
        registry.addInterceptor(adminInterceptor).addPathPatterns("/users").order(2);
    }
}