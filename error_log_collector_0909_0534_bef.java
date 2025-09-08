// 代码生成时间: 2025-09-09 05:34:05
 * using JERSEY Framework in JAVA. It provides an API endpoint 
 * that accepts error logs and stores them into a local file.
 *
 * @author Your Name
 * @version 1.0
 */
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "ErrorLogCollector", urlPatterns = {"/error-log/*"}, loadOnStartup = 1)
@Path("/error-log")
public class ErrorLogCollector extends ResourceConfig {

    private static final Logger LOGGER = Logger.getLogger(ErrorLogCollector.class.getName());
    private static final String ERROR_LOG_FILE = "error_log.txt";

    @POST
    @Path("/collect")
    @Produces(MediaType.TEXT_PLAIN)
    public Response collectErrorLog(String errorLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ERROR_LOG_FILE, true))) {
            writer.write(errorLog);
            writer.newLine();
            LOGGER.info("Error log collected successfully");
            return Response.ok("Error log collected successfully").build();
        } catch (IOException e) {
            LOGGER.severe("Error while collecting error log: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error while collecting error log").build();
        }
    }

    /*
     * Register root resource class and enable MVC feature
     */
    public ErrorLogCollector() {
        packages("com.example.errorlogcollector");
        register(FreemarkerMvcFeature.class);
    }

    public static void main(String[] args) {
        ServletContainer container = new ServletContainer(new ErrorLogCollector());
        container.init();
    }
}