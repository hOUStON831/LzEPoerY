// 代码生成时间: 2025-09-09 01:04:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

@Path("/performance")
public class SystemPerformanceMonitor {

    // 定义系统性能监控的RESTful API
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSystemPerformance() {
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            // 获取系统CPU使用率
            double cpuUsage = osBean.getSystemCpuLoad() * 100;
            // 获取系统可用内存
            long availableMemory = osBean.getFreePhysicalMemorySize() / 1024 / 1024; // 转换为MB
            // 获取系统总内存
            long totalMemory = osBean.getTotalPhysicalMemorySize() / 1024 / 1024; // 转换为MB
            // 构建系统性能监控数据JSON字符串
            String performanceData = "{"cpuUsage": "" + cpuUsage + "%", "availableMemory": "" + availableMemory + "MB", "totalMemory": "" + totalMemory + "MB"}";
            return performanceData;
        } catch (Exception e) {
            // 错误处理
            return "{"error": "Failed to retrieve system performance data"}";
        }
    }
}

// 注意：此代码是一个简单的示例，实际应用中可能需要更复杂的异常处理和性能监控逻辑。