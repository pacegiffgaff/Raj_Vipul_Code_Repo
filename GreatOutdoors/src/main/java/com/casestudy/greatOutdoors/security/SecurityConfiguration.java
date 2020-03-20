package com.casestudy.greatOutdoors.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**Instead of using spring username and password
     * create in memory username & password
     */



    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {


        auth.inMemoryAuthentication().withUser("BookMan").password("{noop}root")
                .roles("USER", "ADMIN");

        auth.inMemoryAuthentication().withUser("test").password("{noop}root")
                .roles("USER", "ADMIN");

        auth.inMemoryAuthentication().withUser("admin").password("{noop}root")
                .roles("USER", "ADMIN");
    }

    /**
     * providing home page after successful login
     * overriding configure method
     * If having user role the only can access
     * For login permit anybody
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/h2-console/**").permitAll()
                .antMatchers("/", "/*customer*/**","/*admin*/**").access("hasRole('USER')").and().formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
