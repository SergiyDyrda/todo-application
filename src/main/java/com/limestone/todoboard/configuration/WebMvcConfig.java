package com.limestone.todoboard.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.limestone.todoboard.web.json.JacksonObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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


    @Bean("objectMapper")
    public ObjectMapper jacksonObjectMapper() {
        return JacksonObjectMapper.getMapper();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(jacksonObjectMapper()));
    }

}