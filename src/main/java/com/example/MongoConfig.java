package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Configuration
public class MongoConfig {
    @Bean
    MongoDatabase mongoDatabase() {
        return MongoClients.create("mongodb://192.168.99.100:27017")
                .getDatabase("demo");
    }
}
