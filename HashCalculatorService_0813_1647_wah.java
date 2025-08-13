// 代码生成时间: 2025-08-13 16:47:07
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * RESTful service for calculating hash values using JERSEY framework.
 */
@Path("/hash")
public class HashCalculatorService {

    // Calculate the hash of a given input string.
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateHash(@QueryParam("input") String input) {
        try {
            // Check if input is provided
            if (input == null || input.trim().isEmpty()) {
                // Return a bad request response if input is missing
                return Response.status(Response.Status.BAD_REQUEST).entity("Input parameter is required.").build();
            }

            // Use SHA-256 for hashing
            String hashAlgorithm = "SHA-256";
            MessageDigest digest = MessageDigest.getInstance(hashAlgorithm);
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert byte array into hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            // Return the hash value as a plain text response
            return Response.ok(hexString.toString()).build();
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where the hash algorithm is not found
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Hash algorithm not supported.").build();
        } catch (Exception e) {
            // Generic exception handling
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while calculating the hash.").build();
        }
    }
}
