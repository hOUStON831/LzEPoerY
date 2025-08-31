// 代码生成时间: 2025-08-31 16:54:40
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/api")
public class ApiResponseFormatter {

    private static final String STATUS_OK = "ok";
    private static final String STATUS_ERROR = "error";
    private static final String ERROR_MESSAGE = "An error occurred while processing your request.";

    @GET
    @Path("/formatResponse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response formatResponse() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("status", STATUS_OK);
        dataMap.put("message", "Response successfully formatted.");
        
        return Response.ok().entity(dataMap).build();
    }

    @GET
    @Path("/handleError")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleError() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", STATUS_ERROR);
        errorMap.put("message", ERROR_MESSAGE);
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMap).build();
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        // This main method is just for testing the class. In a real-world scenario,
        // you would use a JAX-RS server to deploy the application.
    }
}
