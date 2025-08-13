// 代码生成时间: 2025-08-14 04:12:30
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * ApiResponseFormatter is a JAX-RS exception mapper that handles exceptions and returns a
 * formatted response to the client.
 */
@Provider
public class ApiResponseFormatter implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        // Create a map to store the response details
        Map<String, Object> response = new HashMap<>();
        response.put("status", 500); // Default to 500 Internal Server Error
        response.put("message", exception.getMessage());

        // Log the exception or perform any additional error handling
        // This can be expanded to log to a file or an external logging service
        System.err.println("Exception occurred: " + exception.getMessage());

        // Check if the exception is a known type and handle it accordingly
        if (exception instanceof IllegalArgumentException) {
            response.put("status", 400); // Bad Request
            response.put("message", "Invalid input: " + exception.getMessage());
        } else if (exception instanceof NotFoundException) {
            response.put("status", 404); // Not Found
            response.put("message", "Resource not found");
        }

        // Return the formatted response
        return Response.status((Integer) response.get("status"))
                .type(MediaType.APPLICATION_JSON)
                .entity(response)
                .build();
    }
}

/**
 * NotFoundException is a custom exception indicating that a resource was not found.
 */
class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}