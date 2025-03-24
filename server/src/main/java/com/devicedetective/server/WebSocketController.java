package com.devicedetective.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;


/**
 * The WebSocketController manages real-time WebSocket communication for location updates. It provides functionality
 * to receive location data from clients, store it, and distribute updated location information back to clients.
 * It supports operations like receiving new location data, syncing location data among clients, registering new clients,
 * and handling basic messages for interaction or testing purposes.
 *
 * "@CrossOrigin": Allows cross-origin requests to enable WebSocket communication from different domains.
 */
@CrossOrigin
@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * Handles the persistence of location data to MongoDB.
     */
    @Autowired
    private final LocationService locationService;

    /**
     * Handles sending messages over WebSocket channels.
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Caches a list of client IDs to manage the active session and update broadcasting.
     */
    private final ArrayList<String> cachedClientIds;

    /**
     * Constructs the WebSocketController with a dependency on LocationService.
     * Initializes the list of cached client IDs to track active participants.
     *
     * @param locationService The service used for location data management and persistence.
     */
    public WebSocketController(LocationService locationService) {
        this.locationService = locationService;
        this.cachedClientIds = new ArrayList<String>();
    }

    /**
     * Receives location data from a client via WebSocket, logs the latitude and longitude, caches the client ID,
     * saves the location to the database, and broadcasts this location to all connected clients subscribed to "/topic/locations".
     * This method is bound to the WebSocket endpoint "/sendLocation".
     *
     * @param location The location object received from a client, containing latitude, longitude, and client ID.
     */
    @MessageMapping("/sendLocation")
    public void receiveLocation(Location location) {
        logger.info("Received loc: {}", location.getLatitude());
        this.cacheClient(location.getClientId());
        locationService.saveLocation(location);
        messagingTemplate.convertAndSend("/topic/locations", location);
    }

    /**
     * Synchronizes and updates location data among all clients. This method fetches the latest location
     * for each client ID cached, persists any new location data received, and broadcasts the most recent location data
     * to all subscribed clients.
     * This method is bound to the WebSocket endpoint "/syncLocations".
     *
     * @param location Location object representing a recent update that needs to be synchronized.
     */
    @MessageMapping("/syncLocations")
    public void handleLocationUpdates(Location location) {
        // Cache client in case client timed out.
        this.cacheClient(location.getClientId());
        // Save client location to MongoDB.
        locationService.saveLocation(location);

        // Using SiMP, send the location payload individually per client instead of as a list.
        for(String cId : cachedClientIds) {
            messagingTemplate.convertAndSend(
                    "/topic/locations",
                    locationService.findLocationByClientId(cId)
            );
        }
    }

    /**
     * Registers a new client and immediately sends the latest location data available for each cached client ID.
     * This method is bound to the WebSocket endpoint "/registerClient".
     */
    @MessageMapping("/registerClient")
    public void registerNewClient() {
        // Using SiMP, send the location payload individually per client instead of as a list.
        for(String cId : cachedClientIds) {
            messagingTemplate.convertAndSend(
                    "/topic/locations",
                    locationService.findLocationByClientId(cId)
            );
        }
    }

    /**
     * Receives a simple text message from a client, logs it for debugging or interaction tracking.
     * Primarily for debugging purposes.
     *
     * @param message The plain text message received from a client.
     */
    @MessageMapping("/message")
    public void receiveMessage(String message) {
        logger.info("Received message: {}", message);
    }

    /**
     * Caches a client ID if it has not been previously cached. This method ensures that all active clients are tracked
     * for sending updates and avoids duplication in the list of cached IDs.
     *
     * @param clientId The client ID to cache if not already in the list.
     */
    private void cacheClient(String clientId) {
        if(!cachedClientIds.contains(clientId)){
            cachedClientIds.add(clientId);
        }
    }
}