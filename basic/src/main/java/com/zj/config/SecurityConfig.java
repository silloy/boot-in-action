package com.zj.config;

import com.zj.services.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/11
 * Time: 22:26
 * CopyRight: Zhouji
 */
//@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required = false)
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")    // 要求登录者有reader角色
                .antMatchers("/shutdown", "/metrics", "/configprops").access("hasRole('ADMIN')")
                .antMatchers("/**").permitAll()

                .and()

                .formLogin()
                .loginPage("/login")          // 登录表单路径
                .failureUrl("/logn?error=true");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((String username) -> {
            UserDetails user = readerRepository.findOne(username);
            if (Objects.nonNull(user)) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        })
                .and()
                .inMemoryAuthentication()
                .withUser("admin")
                .password("s3cr3t")
                .roles("ADMIN", "READER");
    }

}
