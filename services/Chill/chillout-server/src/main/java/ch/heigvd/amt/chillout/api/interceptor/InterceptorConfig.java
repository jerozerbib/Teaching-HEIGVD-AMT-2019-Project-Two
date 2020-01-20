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
//        registry.addInterceptor(authInterceptor).addPathPatterns("/chillout","/chillout/*").order(1);
//        registry.addInterceptor(adminInterceptor).addPathPatterns("/chillout").order(2);
//        registry.addInterceptor(ownerInterceptor).addPathPatterns("/chillout/*").order(3);
    }

    //TODO get /products ok
}