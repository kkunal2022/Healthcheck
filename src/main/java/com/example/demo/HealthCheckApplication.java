package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kunal
 *
 */
@SpringBootApplication
public class HealthCheckApplication {
	private static final Logger logger = LogManager.getLogger(HealthCheckApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HealthCheckApplication.class, args);
		logger.info("Health Check Spring Boot application started successfully ");
	}

}
