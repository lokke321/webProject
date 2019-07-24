package org.webproject.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.webproject.interceptor.CookieSessionInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Value("${web.chat.attachment.avatars}")
    private String avatarPath;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CookieSessionInterceptor sessionInterceptor() {
        return new CookieSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/mainpage","/registry");
    }

}