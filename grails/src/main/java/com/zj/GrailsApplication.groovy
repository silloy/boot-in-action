package com.zj

import com.zj.entity.ReaderHandlerMethodArgumentResolver
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@ComponentScan
@EnableAutoConfiguration
class GrailsApplication extends WebMvcConfigurerAdapter {

    static void main(String[] args) {
        SpringApplication.run(GrailsApplication.class, args)
    }

    void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/readingList")
        registry.addViewController("/login").setViewName("login")
    }

    void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver())
    }

}