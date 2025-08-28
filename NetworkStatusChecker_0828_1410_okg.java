// 代码生成时间: 2025-08-28 14:10:18
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/network")
public class NetworkStatusChecker {

    /**
     * Endpoint to check the network connection status.
     *
     * @param url The URL to check connectivity against.
     * @return A response indicating whether the network is connected or not.
     */
    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkNetworkConnection(@QueryParam("url") String url) {
        if (url == null || url.isEmpty()) {
            // Return an error response if the URL parameter is missing or empty.
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The 'url' parameter is required.")
                    .build();
        }

        try {
            URL website = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Check the HTTP response code.
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Successfully connected to the network.
                return Response.ok("Network is connected.")
                        .type(MediaType.TEXT_PLAIN)
                        .build();
            } else {
                // The network connection was not successful.
                return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Network is not connected.")
                        .build();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the network check.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while checking the network connection.")
                    .build();
        }
    }
}
