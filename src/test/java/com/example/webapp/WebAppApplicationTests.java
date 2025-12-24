package com.example.webapp;

import com.example.webapp.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * Basic integration test to verify the Spring Boot application context loads successfully.
 */
@SpringBootTest
@Import(TestSecurityConfig.class)
class WebAppApplicationTests {

    @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
    }
}
