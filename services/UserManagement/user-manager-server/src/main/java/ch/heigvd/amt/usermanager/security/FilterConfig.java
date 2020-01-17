package ch.heigvd.amt.usermanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class FilterConfig {

    @Autowired
    JWTProvider provider;

    @Bean
    public FilterRegistrationBean<JWTFilter> logginFilter(){
        FilterRegistrationBean<JWTFilter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new JWTFilter());
    }

}
