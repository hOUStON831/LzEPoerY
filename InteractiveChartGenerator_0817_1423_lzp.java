// 代码生成时间: 2025-08-17 14:23:24
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/chart")
public class InteractiveChartGenerator {

    // 路径：生成交互式图表
    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateInteractiveChart() {
        try {
            // 创建一个Map来存储图表配置和数据
            Map<String, Object> chartConfig = new HashMap<>();

            // 配置图表的基本属性
            chartConfig.put("type", "line"); // 图表类型
            chartConfig.put("title", "Interactive Line Chart"); // 图表标题
            chartConfig.put("xAxis", "Time"); // X轴标签
            chartConfig.put("yAxis", "Value"); // Y轴标签

            // 添加图表数据，这里简单模拟一些数据
            Map<String, Object> series = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            data.put("09:00", 10);
            data.put("10:00", 15);
            data.put("11:00", 20);
            data.put("12:00", 25);
            data.put("13:00", 30);
            series.put("Data", data);
            chartConfig.put("series", series);

            // 返回图表配置和数据
            return Response.ok(chartConfig).build();
        } catch (Exception e) {
            // 错误处理
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to generate interactive chart: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    // 路径：获取图表文档
    @GET
    @Path("/docs")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getChartDocs() {
        String chartDocs = "Interactive Chart Generator Documentation: 
" +
                "1. /chart/generate - Generate an interactive line chart with sample data. 
" +
                "2. /chart/docs - Get this documentation.";
        return Response.ok(chartDocs).build();
    }
}
