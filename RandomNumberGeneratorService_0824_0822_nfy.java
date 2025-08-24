// 代码生成时间: 2025-08-24 08:22:13
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

/**
 * RandomNumberGeneratorService class provides a RESTful service to generate random numbers.
 */
@Path("/random")
public class RandomNumberGeneratorService {

    /**
     * Generates a random number between 1 and 100.
     *
     * @return A response with the random number.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRandomNumber() {
        try {
            Random random = new Random();
            int randomNumber = 1 + random.nextInt(100);  // Generate a random number between 1 and 100.
            return Response.ok("Random Number: " + randomNumber).build();
        } catch (Exception e) {
            // Log the exception and return an error response.
            // In a production environment, consider logging the exception.
            // e.g., logger.log(Level.SEVERE, "Error generating random number", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating random number").build();
        }
    }
}
