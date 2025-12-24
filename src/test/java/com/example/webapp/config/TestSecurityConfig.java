package com.example.webapp.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Test security configuration.
 * Disables OAuth2 and uses basic HTTP security for testing.
 * 
 * Note: CSRF protection is intentionally disabled for testing purposes only.
 * This configuration is never used in production - the main SecurityConfig
 * with full OAuth2 and CSRF protection is used in production environments.
 */
@TestConfiguration
@EnableWebSecurity
public class TestSecurityConfig {

    @Bean
    @Primary
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Public endpoints
                .requestMatchers("/", "/login", "/error", "/css/**", "/js/**", "/images/**").permitAll()
                // API endpoints require authentication (handled by @WithMockUser in tests)
                .requestMatchers("/api/**").authenticated()
                // All other requests require authentication
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {})
            // CSRF is disabled for testing to simplify test setup
            // Production uses the main SecurityConfig with CSRF enabled
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
