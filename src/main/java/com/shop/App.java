package com.shop;

import com.shop.configuration.AppConfiguration;
import com.shop.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(AppConfiguration.class, args);
       context.getBean(Core.class).run();
    }
}