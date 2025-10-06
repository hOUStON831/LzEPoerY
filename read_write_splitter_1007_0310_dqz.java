// 代码生成时间: 2025-10-07 03:10:25
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/db")
public class ReadWriteSplitter {

    // Client to make HTTP requests to databases
    private Client client = ClientBuilder.newClient();
    
    // URL for read operations
    private static final String READ_DB_URL = "http://read-db-host:8080/db/read/";
    
    // URL for write operations
    private static final String WRITE_DB_URL = "http://write-db-host:8080/db/write/";

    @GET
    @Path("/read/{data}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readData(@PathParam("data") String data) {
        try {
            // Build the request to read from the read database
            Invocation.Builder builder = client.target(READ_DB_URL + data).request(MediaType.TEXT_PLAIN);
            Response response = builder.get();
            
            // Check if the response is successful
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return Response.ok(response.readEntity(String.class)).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the read operation
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading data: " + e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/write/{data}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response writeData(@PathParam("data") String data) {
        try {
            // Build the request to write to the write database
            Invocation.Builder builder = client.target(WRITE_DB_URL + data).request(MediaType.TEXT_PLAIN);
            Response response = builder.post(client.target(WRITE_DB_URL + data).request(MediaType.TEXT_PLAIN).build(
                    javax.ws.rs.client.Entity.entity(data, MediaType.TEXT_PLAIN_TYPE)));
            
            // Check if the response is successful
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return Response.ok(response.readEntity(String.class)).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the write operation
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error writing data: " + e.getMessage()).build();
        }
    }

    // Close the client
    public void close() {
        client.close();
    }
}
