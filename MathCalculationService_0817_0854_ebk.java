// 代码生成时间: 2025-08-17 08:54:54
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/math")
public class MathCalculationService {

    // Adds two numbers together
    @GET
    @Path("/add/{x}/{y}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response add(@PathParam("x") double x, @PathParam("y") double y) {
        try {
            double result = x + y;
            return Response.ok("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while adding numbers").build();
        }
    }

    // Subtracts one number from another
    @GET
    @Path("/subtract/{x}/{y}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response subtract(@PathParam("x") double x, @PathParam("y") double y) {
        try {
            double result = x - y;
            return Response.ok("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while subtracting numbers").build();
        }
    }

    // Multiplies two numbers together
    @GET
    @Path("/multiply/{x}/{y}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response multiply(@PathParam("x") double x, @PathParam("y") double y) {
        try {
            double result = x * y;
            return Response.ok("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while multiplying numbers").build();
        }
    }

    // Divides one number by another
    @GET
    @Path("/divide/{x}/{y}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response divide(@PathParam("x") double x, @PathParam("y") double y) {
        try {
            if (y == 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Cannot divide by zero").build();
            }
            double result = x / y;
            return Response.ok("Result: " + result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error occurred while dividing numbers").build();
        }
    }
}
