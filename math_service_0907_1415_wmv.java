// 代码生成时间: 2025-09-07 14:15:37
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path('/math')
public class MathService {

    /*
     * Add two numbers and return the result.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the sum of two numbers
     */
    @GET
    @Path('/add/{num1}/{num2}')
    @Produces(MediaType.TEXT_PLAIN)
    public Response add(@PathParam('num1') int num1, @PathParam('num2') int num2) {
        try {
            int result = num1 + num2;
            return Response.ok(String.valueOf(result)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating sum").build();
        }
    }

    /*
     * Subtract two numbers and return the result.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the difference of two numbers
     */
    @GET
    @Path('/subtract/{num1}/{num2}')
    @Produces(MediaType.TEXT_PLAIN)
    public Response subtract(@PathParam('num1') int num1, @PathParam('num2') int num2) {
        try {
            int result = num1 - num2;
            return Response.ok(String.valueOf(result)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating difference").build();
        }
    }

    /*
     * Multiply two numbers and return the result.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the product of two numbers
     */
    @GET
    @Path('/multiply/{num1}/{num2}')
    @Produces(MediaType.TEXT_PLAIN)
    public Response multiply(@PathParam('num1') int num1, @PathParam('num2') int num2) {
        try {
            int result = num1 * num2;
            return Response.ok(String.valueOf(result)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating product").build();
        }
    }

    /*
     * Divide two numbers and return the result.
     *
     * @param num1 the first number (dividend)
     * @param num2 the second number (divisor)
     * @return the quotient of two numbers
     */
    @GET
    @Path('/divide/{num1}/{num2}')
    @Produces(MediaType.TEXT_PLAIN)
    public Response divide(@PathParam('num1') int num1, @PathParam('num2') int num2) {
        try {
            if (num2 == 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Divisor cannot be zero").build();
            }
            double result = (double) num1 / num2;
            return Response.ok(String.valueOf(result)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error calculating quotient").build();
        }
    }

    // Additional mathematical operations can be added here following the same pattern.
}