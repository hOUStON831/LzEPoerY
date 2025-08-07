// 代码生成时间: 2025-08-07 20:29:17
 * documentation, and maintainability.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/memory")
public class MemoryAnalysisService {

    // The MemoryMXBean provides access to the memory usage of the JVM
    private MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMemoryUsage() {
        try {
            // Get memory usage details
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

            // Create a JSON object to hold the memory usage data
            MemoryUsageData memoryUsageData = new MemoryUsageData();
            memoryUsageData.setHeapMemoryUsage(heapMemoryUsage);
            memoryUsageData.setNonHeapMemoryUsage(nonHeapMemoryUsage);

            // Convert the MemoryUsageData object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(memoryUsageData);
        } catch (Exception e) {
            // Handle any exceptions that may occur and return a JSON error message
            return createErrorResponse(e.getMessage());
        }
    }

    // Helper method to create a JSON error response
    private String createErrorResponse(String errorMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorData errorData = new ErrorData();
            errorData.setError(errorMessage);
            return objectMapper.writeValueAsString(errorData);
        } catch (Exception e) {
            // In case of an error in creating the error response, return a simple error message
            return "{"error":"Failed to create error response."}";
        }
    }
}

/*
 * MemoryUsageData.java
 *
 * This class holds the memory usage data for the heap and non-heap memory.
 */
class MemoryUsageData {
    private MemoryUsage heapMemoryUsage;
    private MemoryUsage nonHeapMemoryUsage;

    public MemoryUsage getHeapMemoryUsage() {
        return heapMemoryUsage;
    }

    public void setHeapMemoryUsage(MemoryUsage heapMemoryUsage) {
        this.heapMemoryUsage = heapMemoryUsage;
    }

    public MemoryUsage getNonHeapMemoryUsage() {
        return nonHeapMemoryUsage;
    }

    public void setNonHeapMemoryUsage(MemoryUsage nonHeapMemoryUsage) {
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
    }
}

/*
 * ErrorData.java
 *
 * This class holds the error data for error responses.
 */
class ErrorData {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
