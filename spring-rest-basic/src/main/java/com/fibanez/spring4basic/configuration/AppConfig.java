package com.fibanez.spring4basic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fibanez.spring4basic.")
public class AppConfig extends WebMvcConfigurerAdapter{

//    @Bean
//    public CustomerValidator beforeCreateWebsiteUserValidator() {
//        return new CustomerValidator();
//    }

}
