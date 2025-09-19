// 代码生成时间: 2025-09-19 21:35:53
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * IntegrationTestService is a class that demonstrates how to create a simple integration test
 * using JERSEY framework.
 */
@Path("/test")
public class IntegrationTestService extends JerseyTest {

    /**
     * Constructor that sets up the JERSEY test environment.
     * @param args command line arguments specific to the test
     */
    public IntegrationTestService(String... args) {
        super(args);
        super.setUp();
    }

    @Override
    protected ResourceConfig configure() {
        // Configure JERSEY resource config
        return new ResourceConfig(IntegrationTestResource.class);
    }

    /**
     * Test the GET request on the resource.
     */
    @Test
    public void testGetRequest() {
        Response response = target("/test/hello").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Expected hello response", "hello", response.readEntity(String.class));
    }
}

/**
 * IntegrationTestResource is a simple REST resource that returns a hello message.
 */
@Path("/test")
class IntegrationTestResource {

    /**
     * Returns a hello message.
     * @return a response containing the hello message.
     */
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getHello() {
        return Response.ok("hello").build();
    }
}
