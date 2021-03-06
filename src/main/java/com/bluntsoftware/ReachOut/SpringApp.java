package com.bluntsoftware.ReachOut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Properties;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringApp.class);
        app.setShowBanner(false);
        Properties props = new Properties();
        props.setProperty("spring.thymeleaf.cache", "false");
        app.setDefaultProperties(props);
        ConfigurableApplicationContext ctx = app.run(args);
    }
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet)     {
        ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
        reg.addUrlMappings("/rest/*");
        reg.addUrlMappings("/rest/error","/error");
        return reg;
    }
}