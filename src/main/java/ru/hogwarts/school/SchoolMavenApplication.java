package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SchoolMavenApplication {
    private static final Logger logger = LoggerFactory.getLogger(SchoolMavenApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SchoolMavenApplication.class, args);
        logger.info("Application successfully run!");
    }

}
