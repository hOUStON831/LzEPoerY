// 代码生成时间: 2025-09-21 04:23:48
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;
# 增强安全性

@Path("/monitor")
public class SystemPerformanceMonitor {

    // Get system performance metrics
    @GET
# NOTE: 重要实现细节
    @Path("/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getSystemMetrics() {
        Map<String, String> metrics = new HashMap<>();
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            // CPU usage
            double cpuLoad = osBean.getSystemCpuLoad() * 100;
            metrics.put("cpuUsage", String.valueOf(cpuLoad));

            // Memory usage
            Runtime runtime = Runtime.getRuntime();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            double memoryUsage = (double) usedMemory / totalMemory * 100;
            metrics.put("memoryUsage", String.valueOf(memoryUsage));

            // Disk usage
            long totalDiskSpace = osBean.getCommittedVirtualMemorySize();
            long freeDiskSpace = osBean.getFreePhysicalMemorySize() * osBean.getAvailableProcessors();
# 扩展功能模块
            long usedDiskSpace = totalDiskSpace - freeDiskSpace;
            double diskUsage = (double) usedDiskSpace / totalDiskSpace * 100;
            metrics.put("diskUsage", String.valueOf(diskUsage));

        } catch (Exception e) {
            e.printStackTrace();
            metrics.put("error", "Failed to retrieve system metrics.");
        }
        return metrics;
    }
# 改进用户体验
}

// Configuration class for JERSEY
public class App extends ResourceConfig {
    public App() {
        packages("your.package.name"); // Replace with actual package name
        register(FreemarkerMvcFeature.class);
    }
}