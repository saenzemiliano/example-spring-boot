package com.example.springboot.demospringboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        
        registry.addViewController("/page/home").setViewName("home");
        registry.addViewController("/page/hello").setViewName("hello");
        registry.addViewController("/page/public/other").setViewName("public/other");
        registry.addViewController("/page/login").setViewName("login");
        
        registry.addRedirectViewController("/", "/page/home");
        registry.addRedirectViewController("/public", "/page/public");
        registry.addRedirectViewController("/page/public", "/page/home");
        registry.addRedirectViewController("/page", "/page/home");
        registry.addRedirectViewController("/page/public/", "/page/home");
        registry.addRedirectViewController("/page/", "/page/home");
        registry.addRedirectViewController("/login", "/page/login");
        registry.addRedirectViewController("/login/", "/page/login");
    }

}
