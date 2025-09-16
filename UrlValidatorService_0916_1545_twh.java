// 代码生成时间: 2025-09-16 15:45:18
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A JAX-RS service endpoint that validates the validity of a given URL.
 */
@Path("/urlvalidator")
public class UrlValidatorService {

    /**
     * Validates a URL and returns a response indicating whether it's valid or not.
     *
     * @param url The URL to be validated.
     * @return A response containing the validity status of the URL.
     */
    @GET
    public Response validateUrl(@QueryParam("url") String url) {
        // Check if the URL is null or empty
        if (url == null || url.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("URL cannot be null or empty.").build();
        }

        try {
            // Create a URI object from the provided URL string
            URI uri = new URI(url);
            // Check if the scheme and host are present
            if (uri.getScheme() == null || uri.getHost() == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL. Scheme and host must be present.").build();
            }
        } catch (URISyntaxException e) {
            // In case of a URISyntaxException, the URL is invalid
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL format.").build();
        }

        // Attempt to create a URL object to further validate the URL
        try {
            new URL(url);
            return Response.ok("Valid URL.").build();
        } catch (Exception e) {
            // If a MalformedURLException is caught, the URL is invalid
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL.").build();
        }
    }
}
