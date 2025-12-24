package com.example.webapp;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * Main Quarkus application class for the web application skeleton.
 * This application provides:
 * - RESTful API endpoints for data access using JAX-RS (JakartaEE)
 * - PostgreSQL database integration with Flyway migrations
 * - OAuth 2.0/OIDC authentication
 * - Server-side rendering with Qute templates
 */
@QuarkusMain
public class WebAppApplication implements QuarkusApplication {

    public static void main(String[] args) {
        Quarkus.run(WebAppApplication.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        Quarkus.waitForExit();
        return 0;
    }
}
