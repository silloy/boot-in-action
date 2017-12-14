package com.zj.controller

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/13
 * Time: 14:00
 * CopyRight: Zhouji
 */
@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")    // 要求登录者有reader角色
                .antMatchers("/**").permitAll()

                .and()

                .formLogin()
                .loginPage("/login")          // 登录表单路径
                .failureUrl("/logn?error=true");
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                {username -> Reader.findByUsername(username)}
                as UserDetailsService
        );
    }
}