// 代码生成时间: 2025-10-04 02:14:25
 * It follows best practices for Java development, error handling, and maintainability.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/url-validation")
public class UrlValidationService {

    /**
     * Validates the provided URL.
     *
     * @param urlString The URL to validate as a query parameter.
     * @return A response object indicating the validity of the URL.
     */
    @GET
    public Response validateUrl(@QueryParam("url") String urlString) {
        if (urlString == null || urlString.trim().isEmpty()) {
            // Return error response for invalid or empty URL parameter
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid or empty URL parameter.")
                    .build();
        }

        try {
            // Create URL object from the provided string
            URL url = new URL(urlString);

            // Open connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds timeout
            connection.setReadTimeout(5000); // 5 seconds timeout

            // Check the HTTP response code to determine the validity of the URL
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                // URL is valid if the response code is between 200 and 299
                return Response.ok("URL is valid.").build();
            } else {
                // URL is not valid if the response code is outside the 200-299 range
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("URL is not valid.")
                        .build();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the URL validation process
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while validating the URL: " + e.getMessage())
                    .build();
        }
    }
}