// 代码生成时间: 2025-10-13 21:43:43
package com.example.keymanagement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/keys")
public class KeyManagementService {

    private final Map<String, String> keys = new HashMap<>();

    /**
     * Generates a new key and stores it in the service.
     * 
     * @param keyData The data used to generate the key.
     * @return The generated key and its value.
     */
    @POST
    @Path("/generate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateKey(String keyData) {
        try {
            String generatedKey = generateNewKey(keyData);
            keys.put(generatedKey, keyData);
            return Response.ok().entity(new KeyDTO(generatedKey, keyData)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Retrieves a key by its ID.
     * 
     * @param keyId The ID of the key to retrieve.
     * @return The key and its data if found, otherwise a 404 error.
     */
    @GET
    @Path("/{keyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKey(@PathParam("keyId\) String keyId) {
        if (keys.containsKey(keyId)) {
            return Response.ok().entity(new KeyDTO(keyId, keys.get(keyId))).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Key not found.").build();
        }
    }

    /**
     * Deletes a key by its ID.
     * 
     * @param keyId The ID of the key to delete.
     * @return 200 OK if the key is deleted, otherwise a 404 error.
     */
    @DELETE
    @Path("/{keyId}")
    public Response deleteKey(@PathParam("keyId\) String keyId) {
        if (keys.remove(keyId) != null) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Key not found.").build();
        }
    }

    /**
     * Generates a new key based on the provided data.
     * This is a mock method for generating a key.
     * 
     * @param keyData The data used to generate the key.
     * @return A new key string.
     */
    private String generateNewKey(String keyData) {
        // This is a placeholder for actual key generation logic.
        return "key" + System.currentTimeMillis() + "-" + keyData;
    }

    /**
     * Data Transfer Object for key information.
     */
    public static class KeyDTO {
        private String id;
        private String value;

        public KeyDTO(String id, String value) {
            this.id = id;
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }
}
