package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.servlet31.HttpHandlerServlet;
import org.springframework.web.reactive.DispatcherHandler;

@SpringBootApplication
public class DemoSpringReactiveApplication {

    @Bean
    HttpHandlerServlet httpHandlerServlet(ApplicationContext context) {
        DispatcherHandler dispatcherHandler = new DispatcherHandler();
        dispatcherHandler.setApplicationContext(context);

        HttpHandlerServlet servlet = new HttpHandlerServlet();
        servlet.setHandler(dispatcherHandler);
        return servlet;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringReactiveApplication.class, args);
    }
}
