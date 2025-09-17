// 代码生成时间: 2025-09-17 08:51:34
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/permissions")
public class UserPermissionService {

    // A map to simulate a database of user permissions
    private Map<Integer, Map<String, Boolean>> userPermissions = new HashMap<>();

    // Initialize some example permissions
    public UserPermissionService() {
        Map<String, Boolean> user1Permissions = new HashMap<>();
        user1Permissions.put("READ", true);
        user1Permissions.put("WRITE", false);
        user1Permissions.put("DELETE", false);
        userPermissions.put(1, user1Permissions);

        Map<String, Boolean> user2Permissions = new HashMap<>();
        user2Permissions.put("READ", true);
        user2Permissions.put("WRITE", true);
        user2Permissions.put("DELETE", true);
        userPermissions.put(2, user2Permissions);
    }

    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkPermission(@QueryParam("userId") int userId, @QueryParam("permission") String permission) {
        Map<String, Boolean> permissions = userPermissions.get(userId);
        if (permissions == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        Boolean hasPermission = permissions.get(permission);
        if (hasPermission == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Permission not found").build();
        }
        return Response.ok().entity(Map.of("userId", userId, "permission", permission, "hasPermission", hasPermission)).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePermission(PermissionUpdate payload) {
        try {
            int userId = payload.getUserId();
            String permission = payload.getPermission();
            Boolean value = payload.getPermissionValue();
            Map<String, Boolean> permissions = userPermissions.getOrDefault(userId, new HashMap<>());
            permissions.put(permission, value);
            userPermissions.put(userId, permissions);
            return Response.ok().entity(Map.of("userId", userId, "permission", permission, "updatedValue", value)).build();
        } catch (Exception e) {
            return Response.serverError().entity("Error updating permission").build();
        }
    }

    // A helper class to handle permission update requests
    public static class PermissionUpdate {
        private int userId;
        private String permission;
        private Boolean permissionValue;

        public PermissionUpdate() {}

        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public String getPermission() { return permission; }
        public void setPermission(String permission) { this.permission = permission; }

        public Boolean getPermissionValue() { return permissionValue; }
        public void setPermissionValue(Boolean permissionValue) { this.permissionValue = permissionValue; }
    }
}
