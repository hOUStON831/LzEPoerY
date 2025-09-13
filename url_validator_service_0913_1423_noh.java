// 代码生成时间: 2025-09-13 14:23:07
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.net.MalformedURLException;

// URLValidatorService is a JAX-RS resource class for validating URL links
@Path("/urlValidator")
public class URLValidatorService {

    // Validates the URL using the @GET method and @QueryParam
    @GET
    public Response validateURL(@QueryParam("url") String urlString) {
        try {
            // Create a URL object from the provided string
            URL url = new URL(urlString);
            // If the URL is valid, return a success response with a message
            return Response.ok("The URL is valid: " + url.toString()).build();
        } catch (MalformedURLException e) {
            // If the URL is invalid, return an error response with the exception message
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL: " + e.getMessage()).build();
        }
    }
}
