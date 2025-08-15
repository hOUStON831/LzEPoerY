// 代码生成时间: 2025-08-15 17:55:23
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// NotificationService is a RESTful service that handles message notifications.
@Path("/notification")
public class NotificationService {
    // A concurrent map to store notifications, with a unique ID for each notification.
    private static final ConcurrentHashMap<Integer, String> notifications = new ConcurrentHashMap<>();
    private static final AtomicInteger notificationId = new AtomicInteger(1);

    // POST method to create a new notification.
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response createNotification(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Notification message cannot be empty.").build();
            }

            int id = notificationId.getAndIncrement();
            notifications.put(id, message);
            return Response.status(Response.Status.CREATED).entity("Notification created with ID: " + id).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating notification: " + e.getMessage()).build();
        }
    }

    // GET method to get a notification by ID.
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getNotification(@javax.ws.rs.core.Context javax.ws.rs.core.UriInfo uriInfo, @PathParam("id") int id) {
        try {
            String message = notifications.get(id);
            if (message == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Notification not found with ID: " + id).build();
            }
            return Response.status(Response.Status.OK).entity(message).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving notification: " + e.getMessage()).build();
        }
    }
}
