package com.example.springboot.demospringboot;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.example.springboot.demospringboot.common.Constant;

@SpringBootApplication
public class DemoSpringBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
    	ApplicationContext applicationContext = SpringApplication.run(DemoSpringBootApplication.class, args);
    	/*for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}*/
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            /*System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("*" + beanName);
            }*/

        };
    }
    /*
    @Profile("dev")
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory(){
         LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName(Constant.PERSIST_UNIT_NAME);
        return factoryBean;
    }*/
}
