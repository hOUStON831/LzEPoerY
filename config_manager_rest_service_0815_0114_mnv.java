// 代码生成时间: 2025-08-15 01:14:13
 * It provides endpoints to list, get, update, and delete configurations.
 */

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/config")
public class ConfigManagerRestService {

    // A simple in-memory store to simulate a configuration file storage
    private final Map<String, String> configStore = new HashMap<>();

    // Constructor
    public ConfigManagerRestService() {
        // Initialize with some default configurations
        configStore.put("database.url", "jdbc:mysql://localhost:3306/mydb");
        configStore.put("server.port", "8080");
    }

    /**
     * GET endpoint to list all configuration keys.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listConfigs() {
        try {
            return Response.ok(configStore.keySet()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error listing configurations: " + e.getMessage()).build();
        }
    }

    /**
     * GET endpoint to retrieve a single configuration value by key.
     * @param key The configuration key to retrieve.
     */
    @GET
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getConfigValue(@PathParam("key") String key) {
        try {
            String value = configStore.get(key);
            if (value == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Configuration not found.").build();
            }
            return Response.ok(value).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error retrieving configuration: " + e.getMessage()).build();
        }
    }

    /**
     * PUT endpoint to update a configuration value by key.
     * @param key The configuration key to update.
     * @param value The new value for the configuration.
     */
    @PUT
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateConfigValue(@PathParam("key") String key, String value) {
        try {
            if (configStore.containsKey(key)) {
                configStore.put(key, value);
                return Response.ok("Configuration updated successfully.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Configuration key not found.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Error updating configuration: " + e.getMessage()).build();
        }
    }

    /**
     * DELETE endpoint to delete a configuration by key.
     * @param key The configuration key to delete.
     */
    @DELETE
    @Path("/{key}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteConfig(@PathParam("key") String key) {
        try {
            if (configStore.remove(key) != null) {
                return Response.ok("Configuration deleted successfully.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Configuration not found.").build();
            }
        } catch (Exception e) {
            return Response.serverError().entity("Error deleting configuration: " + e.getMessage()).build();
        }
    }
}
