// 代码生成时间: 2025-08-25 15:42:35
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

/**
 * RandomNumberGeneratorService is a RESTful web service that generates a random number.
 */
@Path("/random")
public class RandomNumberGeneratorService {

    private final Random random = new Random();

    /**
     * Returns a random number between 1 and 100.
     *
     * @return A JSON response containing the random number.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomNumber() {
        try {
            int randomNumber = random.nextInt(100) + 1; // Generate a number between 1 and 100
            return String.format("{"randomNumber": "%d"}", randomNumber);
        } catch (Exception e) {
            // Log and handle any exceptions that may occur
            return String.format("{"error": "An error occurred: %s"}", e.getMessage());
        }
    }
}
