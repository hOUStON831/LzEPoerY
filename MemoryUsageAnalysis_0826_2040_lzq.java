// 代码生成时间: 2025-08-26 20:40:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;

@Path("/memory")
public class MemoryUsageAnalysis {

    // MXBean for getting memory information
    private static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    private static Map<String, MemoryUsage> getMemoryUsage() {
        Map<String, MemoryUsage> memoryUsageMap = new HashMap<>();

        // Heap Memory Usage
        memoryUsageMap.put("heap", memoryMXBean.getHeapMemoryUsage());

        // Non-Heap Memory Usage
        memoryUsageMap.put("nonHeap", memoryMXBean.getNonHeapMemoryUsage());

        return memoryUsageMap;
    }

    @GET
    @Path("/usage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMemoryUsage() {
        try {
            Map<String, MemoryUsage> memoryUsageMap = getMemoryUsage();
            // Construct JSON response
            String jsonResponse = "{
"
            // Add heap memory usage information to the JSON response
            + ""heapUsed": " + memoryUsageMap.get("heap").getUsed() + ",
"
            + ""heapMax": " + memoryUsageMap.get("heap").getMax() + ",
"
            + ""heapCommitted": " + memoryUsageMap.get("heap").getCommitted() + "
"
            // Add non-heap memory usage information to the JSON response
            + ""nonHeapUsed": " + memoryUsageMap.get("nonHeap").getUsed() + ",
"
            + ""nonHeapMax": " + memoryUsageMap.get("nonHeap").getMax() + ",
"
            + ""nonHeapCommitted": " + memoryUsageMap.get("nonHeap").getCommitted() + "
"
            + "}";

            return Response.status(Response.Status.OK).entity(jsonResponse).build();

        } catch (Exception e) {
            // Log the exception and return a 500 Internal Server Error
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error").build();
        }
    }
}
