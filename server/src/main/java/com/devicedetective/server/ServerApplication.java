package com.devicedetective.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main entry point for the DeviceDetective server application.
 * This class configures and launches the Spring Boot application.
 * It enables MongoDB repositories which are used to interact with MongoDB databases.
 *
 * "@SpringBootApplication": Marks this class as a Spring Boot application with autoconfiguration enabled.
 * "@EnableMongoRepositories": Enables the scanning for Spring Data repositories that interface with MongoDB.
 */
@SpringBootApplication
@EnableMongoRepositories
public class ServerApplication {

	// Logger for this class, used to log messages. Configured to use the WebSocketController's logger context.
	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	/**
	 * Main method to launch the Spring Boot application.
	 * Logs a startup message indicating that the application has started successfully.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		logger.info("Server started");
	}
}
