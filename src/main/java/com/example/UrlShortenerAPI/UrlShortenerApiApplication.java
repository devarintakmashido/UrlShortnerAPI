package com.example.UrlShortenerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching     // Turns on Redis Caching
@EnableScheduling  // Turns on Background Tasks
public class UrlShortenerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApiApplication.class, args);
    }
}