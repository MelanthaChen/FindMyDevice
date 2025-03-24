package com.devicedetective.server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a geographic location for a client in the system. This class
 * models the data exchanged between the client
 * and the "test" database in MongoDB. It stores client identifiers along with
 * their current geographic coordinates.
 */
@Document("test")
public class Location {

    /**
     * The unique identifier for the location record, automatically assigned by
     * MongoDB.
     * It also encodes the creation timestamp. "@Id" annotation indicates this field
     * is the primary key in the database.
     */
    @Id
    private Object id;

    /**
     * A unique identifier (UUID) for the client session, ensuring that each session
     * is uniquely tracked.
     */
    private String clientId;

    /**
     * The geographical latitude of the client's current location.
     */
    private String latitude;

    /**
     * The geographical longitude of the client's current location.
     */
    private String longitude;

    public Location() {
    }

    /**
     * Retrieves the unique ID of the location.
     * 
     * @return the MongoDB assigned ID
     */
    public Object getId() {
        return id;
    }

    /**
     * Updates the ID of the location.
     * 
     * @param id the new ID
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     * Retrieves the client identifier.
     * 
     * @return clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the new client identifier.
     * 
     * @param clientId the UUID to set as clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the latitude of the client's location.
     * 
     * @return latitude as a String
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the client's location.
     * 
     * @param latitude the new latitude value
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Retrieves the longitude of the client's location.
     * 
     * @return longitude as a String
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the client's location.
     * 
     * @param longitude the new longitude value
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}