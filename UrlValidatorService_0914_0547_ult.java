// 代码生成时间: 2025-09-14 05:47:59
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.net.MalformedURLException;

@Path("/url")
public class UrlValidatorService extends Application {

    /**
     * Validates the given URL and returns a response.
     *
     * @param url The URL to be validated.
     * @return A response indicating whether the URL is valid or not.
     */
    @GET
    @Path("/validate")
    public Response validateUrl(@QueryParam("url") String url) {
        try {
            // Attempt to create a new URL object. If successful, the URL is valid.
            new URL(url).toURI();
            return Response.status(Response.Status.OK).entity("URL is valid.").build();
        } catch (MalformedURLException | IllegalArgumentException e) {
            // If an exception is caught, the URL is invalid.
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL.").build();
        } catch (Exception e) {
            // Handle any other exceptions as server errors.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error: " + e.getMessage()).build();
        }
    }
}
