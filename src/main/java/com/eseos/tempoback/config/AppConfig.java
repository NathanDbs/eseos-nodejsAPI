package com.eseos.tempoback.config;

import com.eseos.tempoback.interceptor.LogInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration file for the application
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    LogInterceptor logInterceptor;

    /**
     * Add interceptor in the registry
     * @param registry the interceptor registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }

    /**
     * The model mapper bean
     * @return model mapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
