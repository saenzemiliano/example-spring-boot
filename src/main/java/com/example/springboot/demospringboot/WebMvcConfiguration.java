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
        registry.addViewController("/page/login").setViewName("login");
        registry.addViewController("/page/public/other").setViewName("public/other");
        registry.addViewController("/page/public/change-pwd").setViewName("public/change-pwd");
        registry.addViewController("/page/public/reset-pwd").setViewName("public/reset-pwd");
        registry.addViewController("/page/public/confirm-change-pwd").setViewName("public/confirm-change-pwd");
        registry.addViewController("/page/public/confirm-reset-pwd").setViewName("public/confirm-reset-pwd");
        
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
