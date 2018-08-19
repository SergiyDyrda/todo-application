package com.limestone.todoboard.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final HandlerInterceptor authorizedUserInterceptor;

    @Autowired
    public WebMvcConfig(HandlerInterceptor authorizedUserInterceptor) {
        this.authorizedUserInterceptor = authorizedUserInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizedUserInterceptor);
    }

}