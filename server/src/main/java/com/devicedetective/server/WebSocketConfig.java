package com.devicedetective.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * Configuration class for setting up WebSocket communication using Spring.
 * Enables WebSocket message handling, backed by a message broker.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Register WebSocket endpoints that the clients will use to connect to the server.
     * Configures a single endpoint for WebSocket communication.
     *
     * @param registry the STOMP endpoint registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register "/ws" as a new endpoint that clients can connect to. Enable SockJS fallback options.
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /**
     * Configure the message broker that will be used to route messages from one client to another.
     * Defines the application destination prefixes and sets up a simple broker for topic-based messaging.
     *
     * @param registry the message broker registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a memory-based message broker to carry the messages back to the client on destinations prefixed "/topic".
        registry.enableSimpleBroker("/topic");
        // Designate the "/app" prefix for messages bound for methods annotated with @MessageMapping.
        registry.setApplicationDestinationPrefixes("/app");
    }


}