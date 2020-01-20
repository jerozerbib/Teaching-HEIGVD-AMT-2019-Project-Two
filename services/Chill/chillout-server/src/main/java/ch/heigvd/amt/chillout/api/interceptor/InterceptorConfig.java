package ch.heigvd.amt.chillout.api.interceptor;

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



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/orders", "/orders/*", "/products", "/products/*", "/clients", "/clients/*").order(1);
        registry.addInterceptor(adminInterceptor).addPathPatterns("/orders", "/products", "/clients").order(2);
        registry.addInterceptor(ownerInterceptor).addPathPatterns("/clients/*").order(3);
    }
}