package com.shop;

import com.shop.configuration.AppConfiguration;
import com.shop.core.impl.Core;

import com.shop.db.DbConnect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
       DbConnect.initSchema();
       DbConnect.initData();

       ApplicationContext context = SpringApplication.run(AppConfiguration.class, args);
       context.getBean(Core.class).run();
    }
}