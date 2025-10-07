// 代码生成时间: 2025-10-08 00:00:21
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationPath("/api")
public class DefectTrackingSystem extends Application {
    
    // List to store defects for simplicity
    private List<Defect> defects = new ArrayList<>();

    @Path("/defects")
    public class DefectResource {

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllDefects() {
            try {
                return Response.ok(defects).build();
            } catch (Exception e) {
                return Response.serverError().entity("Error retrieving defects").build();
            }
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addDefect(Defect defect) {
            try {
                if (defect == null || defect.getDescription() == null || defect.getDescription().isEmpty()) {
                    return Response.status(Response.Status.BAD_REQUEST).entity("Invalid defect data").build();
                }
                
                defect.setId(UUID.randomUUID().toString());
                defects.add(defect);
                return Response.status(Response.Status.CREATED).entity(defect).build();
            } catch (Exception e) {
                return Response.serverError().entity("Error adding defect").build();
            }
        }
    }

    // Defect class to hold defect details
    public static class Defect {
        private String id;
        private String description;
        private String status;
        private String severity;
        private String assignedTo;
        private String createdOn;

        // Constructors, getters, and setters omitted for brevity
    }
}
