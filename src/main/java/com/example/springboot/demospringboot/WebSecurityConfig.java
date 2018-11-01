package com.example.springboot.demospringboot;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/resources/**", "/page/public/**").permitAll()
                .antMatchers("/repo/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/page/login")
                .permitAll()
            .and()
                .logout()
                .permitAll();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        UserDetails user =
                User.withUsername("user")
                .password("password")
                .roles("USER", "OPERATOR")
                .passwordEncoder(x -> new BCryptPasswordEncoder().encode(x))
                .build();
         
        UserDetails admin =
                User.withUsername("admin")
                .password("password")
                .roles("USER", "OPERATOR", "ADMIN")
                .passwordEncoder(x -> new BCryptPasswordEncoder().encode(x))
                .build();
        

        addUser(jdbcUserDetailsManager, user);
        addUser(jdbcUserDetailsManager, admin);
        
        return jdbcUserDetailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user
//                = User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    private void addUser(JdbcUserDetailsManager jdbcUserDetailsManager, UserDetails user) {
        if (!jdbcUserDetailsManager.userExists(user.getUsername())) {
            jdbcUserDetailsManager.createUser(user);
        } else {
            jdbcUserDetailsManager.updateUser(user);
        }
    }
}
