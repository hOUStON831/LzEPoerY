// 代码生成时间: 2025-08-02 21:05:33
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * MemoryAnalysisService provides an endpoint to analyze and retrieve
 * memory usage statistics.
 */
@Path("/memory")
public class MemoryAnalysisService {

    /**
     * Returns memory usage statistics in JSON format.
     *
     * @return Memory usage statistics.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMemoryUsage() {
        try {
            // Get the memory MX bean to access memory usage statistics
            MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();

            // Get the memory usage
            MemoryUsage heapMemoryUsage = mxBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = mxBean.getNonHeapMemoryUsage();

            // Create a JSON object to hold the memory usage statistics
            StringBuilder memoryUsageJson = new StringBuilder();
            memoryUsageJson.append("{\"heapMemoryUsage\":{\"init\":\"").append(heapMemoryUsage.getInit())
                    .append("\",\"used\":\"").append(heapMemoryUsage.getUsed())
                    .append("\",\