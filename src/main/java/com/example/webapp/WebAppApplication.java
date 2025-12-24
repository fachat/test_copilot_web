package com.example.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the web application skeleton.
 * This application provides:
 * - RESTful API endpoints for data access
 * - PostgreSQL database integration
 * - OAuth 2.0 authentication (GitHub)
 * - Server-side rendering with Thymeleaf
 */
@SpringBootApplication
public class WebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }
}
