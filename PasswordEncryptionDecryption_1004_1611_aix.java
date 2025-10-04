// 代码生成时间: 2025-10-04 16:11:46
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Path("/password")
public class PasswordEncryptionDecryption {

    // Encryption method
    @POST
    @Path("/encrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response encryptPassword(String password) {
        try {
            String encryptedPassword = encrypt(password);
            return Response.ok(encryptedPassword).build();
        } catch (NoSuchAlgorithmException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Encryption algorithm not found").build();
        }
    }

    // Decryption method (for demonstration purposes, actual decryption is not possible with hash functions)
    @POST
    @Path("/decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response decryptPassword(String encryptedPassword) {
        // In a real-world scenario, decryption is not possible with a hash function.
        // This method is included for demonstration purposes only.
        return Response.ok("Decryption not possible with hash functions").build();
    }

    // Helper method to encrypt the password using SHA-256
    private String encrypt(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        return Base64.getEncoder().encodeToString(bytes);
    }

    // Main method for testing the REST service
    public static void main(String[] args) {
        // Here you would set up your Jersey server and configure the REST endpoint
        // For example, using Grizzly or Jetty as the server
    }
}
