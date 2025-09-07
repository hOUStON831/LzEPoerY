// 代码生成时间: 2025-09-08 00:45:08
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.server.ResourceConfig;

@Path("/webContent")
public class WebContentGrabber {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String fetchWebContent(@QueryParam("url") String urlString) {
        // Check if the URL is provided
        if (urlString == null || urlString.isEmpty()) {
            return "Error: No URL provided.";
        }

        try {
            // Create a URL object
            URL url = new URL(urlString);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method
            connection.setRequestMethod("GET");

            // Check if the request was successful
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Error: Failed to retrieve content. HTTP status code: " + connection.getResponseCode();
            }

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\
");
            }
            reader.close();

            // Return the web content
            return response.toString();
        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            return "Error: An exception occurred while fetching web content.\
" + e.getMessage();
        }
    }
}

// Resource configuration class
public class WebContentApp extends ResourceConfig {
    public WebContentApp() {
        // Register the resource class
        register(WebContentGrabber.class);
    }
}