// 代码生成时间: 2025-09-11 15:35:53
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A RESTful service that calculates the hash value of a given input.
 */
@Path("/hash")
public class HashCalculatorService {

    private static final Logger LOGGER = Logger.getLogger(HashCalculatorService.class.getName());

    /**
     * Calculate the hash value of the input text.
     *
     * @param input The text to be hashed.
     * @return The hash value of the input text.
     */
    @GET
    public Response calculateHash(@QueryParam("input") String input) {
        if (input == null || input.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Input cannot be null or empty").build();
        }
        try {
            String hashAlgorithm = "SHA-256"; // You can change the algorithm as needed
            MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm);
            byte[] hashBytes = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            return Response.ok().entity("Hash value: " + hashBuilder.toString()).build();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "Hash algorithm not found", e);
            return Response.serverError().entity("Hash algorithm not found").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error calculating hash", e);
            return Response.serverError().entity("Error calculating hash").build();
        }
    }
}
