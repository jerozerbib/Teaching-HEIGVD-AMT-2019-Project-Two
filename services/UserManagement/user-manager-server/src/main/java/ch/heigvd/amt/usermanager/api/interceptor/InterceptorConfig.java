package ch.heigvd.amt.usermanager.api.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    AdminInterceptor adminInterceptor;

    @Autowired
    OwnerInterceptor ownerInterceptor;

    /**
     * Adds the rules to the Interceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/users","/users/*").order(1);
        registry.addInterceptor(adminInterceptor).addPathPatterns("/users").order(2);
        registry.addInterceptor(ownerInterceptor).addPathPatterns("/users/*").order(3);
    }
}