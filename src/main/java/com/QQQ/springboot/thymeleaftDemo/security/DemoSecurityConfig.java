package com.QQQ.springboot.thymeleaftDemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder users = User.withDefaultPasswordEncoder();
        
        //memory authentication
        
        auth.inMemoryAuthentication()
            .withUser(users.username("john").password("test123").roles("EMPLOYEE"))
            .withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
            .withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .antMatchers("/employees/showForm*").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers("/employees/delete").hasRole("ADMIN")
            .antMatchers("/employees/**").hasRole("EMPLOYEE")
            .antMatchers("/resources/**").permitAll()
            .and()
            .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
    }
    
    
}
