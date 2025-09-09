// 代码生成时间: 2025-09-09 15:15:31
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Path("/hash")
public class HashCalculatorService {

    private static final Logger LOGGER = Logger.getLogger(HashCalculatorService.class.getName());

    @POST
    @Path("/{algorithm}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response calculateHash(@PathParam("algorithm") String algorithm, String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(input.getBytes());
            return Response.ok(
                Base64.getEncoder().encodeToString(hash)
            ).build();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("Hash algorithm not supported: " + algorithm);
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Hash algorithm not supported: " + algorithm)
                .build();
        } catch (Exception e) {
            LOGGER.severe("Error calculating hash: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error calculating hash: " + e.getMessage())
                .build();
        }
    }

    @GET
    @Path("/supported")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSupportedAlgorithms() {
        try {
            MessageDigest[] algorithms = MessageDigest.getInstances();
            String[] supportedAlgorithms = new String[algorithms.length];
            for (int i = 0; i < algorithms.length; i++) {
                supportedAlgorithms[i] = algorithms[i].getAlgorithm();
            }
            return Response.ok(supportedAlgorithms).build();
        } catch (Exception e) {
            LOGGER.severe("Error retrieving supported algorithms: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error retrieving supported algorithms: " + e.getMessage())
                .build();
        }
    }
}