// 代码生成时间: 2025-10-02 03:10:25
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.glassfish.jersey.server.ResourceConfig;

@Path("/webcontent")
public class WebContentGrabber {

    @GET
    @Path("/grab")
    @Produces(MediaType.TEXT_HTML)
    public Response grabWebContent(@QueryParam("url") String url) {
        if (url == null || url.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("URL parameter is required.").build();
        }

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // Execute the HTTP GET request
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // Return the entity content as a string
                    return Response.ok(entity.getContent(), MediaType.TEXT_HTML_TYPE).build();
                } else {
                    return Response.status(Response.Status.NO_CONTENT).entity("No content found.").build();
                }
            }
        } catch (IOException e) {
            // Handle IOException
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error fetching web content: " + e.getMessage()).build();
        }
    }
}

/*
 * WebContentGrabberApplication.java
 * Main application class that sets up the JERSEY application.
 */
import org.glassfish.jersey.server.ResourceConfig;

public class WebContentGrabberApplication extends ResourceConfig {
    public WebContentGrabberApplication() {
        packages("your.package.name"); // Replace with your actual package name
    }
}
