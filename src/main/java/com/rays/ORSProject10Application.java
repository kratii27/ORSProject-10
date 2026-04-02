package com.rays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Entry point for the ORS Project 10 Spring Boot application.
 * <p>
 * This class bootstraps the application and configures global
 * Cross-Origin Resource Sharing (CORS) settings to allow communication
 * between the backend and permitted frontend origins.
 * </p>
 *
 * @author Krati Trivedi
 */
@SpringBootApplication
public class ORSProject10Application {

    /**
     * Main method that launches the Spring Boot application.
     *
     * @param args command-line arguments passed at application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(ORSProject10Application.class, args);
    }

    /**
     * Configures global CORS mappings for the application.
     * <p>
     * Allows cross-origin requests from {@code http://localhost:4200} (Angular dev server)
     * and {@code http://localhost:80} for the specified HTTP methods.
     * All headers are permitted and credentials are allowed.
     * </p>
     *
     * @return a {@link WebMvcConfigurer} instance with CORS settings applied
     */
    @Bean
    public WebMvcConfigurer corsConfig() {
        WebMvcConfigurer w = new WebMvcConfigurer() {
             /**
             * Registers CORS mappings to allow cross-origin requests
             * from permitted origins with the specified HTTP methods and headers.
             *
             * @param registry the {@link CorsRegistry} used to define CORS rules
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
            }
        };
        return w;
    }
}