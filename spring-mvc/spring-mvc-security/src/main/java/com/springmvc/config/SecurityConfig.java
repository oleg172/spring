package com.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/admin-page").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/user-page").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .and()
                .formLogin().loginPage("/user/login").failureUrl("/user/login?error")
                .usernameParameter("name").passwordParameter("password")
                .and()
                .csrf()
                .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN");
        builder.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER");
    }

}
