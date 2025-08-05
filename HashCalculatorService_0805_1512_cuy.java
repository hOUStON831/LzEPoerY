// 代码生成时间: 2025-08-05 15:12:21
 * A service class that provides functionality to calculate hash values for given input strings.
 * It follows Java best practices, includes error handling, and is well-documented for maintainability and scalability.
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Path("/hash")
public class HashCalculatorService {

    /*
     * Calculates the hash value of a given string input using a specified algorithm.
     *
     * @param input The string to be hashed.
     * @param algorithm The hashing algorithm to use (e.g., SHA-256).
     * @return A Base64-encoded string representing the hash value.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     */
    @POST
    @Path("/{algorithm}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateHash(@PathParam("algorithm") String algorithm, String input) throws NoSuchAlgorithmException {
        try {
            // Get the appropriate MessageDigest instance based on the algorithm
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // Update the message digest with the input bytes
            digest.update(input.getBytes());

            // Calculate the hash value
            byte[] hashBytes = digest.digest();

            // Encode the hash bytes as a Base64 string and return it
            return Response.ok(Base64.getEncoder().encodeToString(hashBytes)).build();

        } catch (NoSuchAlgorithmException e) {
            // Handle the case where the algorithm is not found
            return Response.status(Response.Status.BAD_REQUEST).entity("Hashing algorithm not found: " + algorithm).build();
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred while calculating the hash: " + e.getMessage()).build();
        }
    }
}
