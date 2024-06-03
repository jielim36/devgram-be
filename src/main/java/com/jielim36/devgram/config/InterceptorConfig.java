package com.jielim36.devgram.config;

import com.jielim36.devgram.interceptor.UnauthorizedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    private final UnauthorizedInterceptor unauthorizedInterceptor;

    public InterceptorConfig(UnauthorizedInterceptor unauthorizedInterceptor) {
        this.unauthorizedInterceptor = unauthorizedInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(unauthorizedInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/oauth2/authorization/*", "/oauth2/callback/*", "/error/**");
    }

}
