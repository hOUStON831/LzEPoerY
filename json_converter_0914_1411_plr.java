// 代码生成时间: 2025-09-14 14:11:29
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/json")
public class JsonConverter {

    // 线程安全的计数器
    private static final AtomicInteger counter = new AtomicInteger();

    // POST 方法，接受 JSON 数据并转换
    @POST
    @Path("/convert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertJson(String jsonInput) {
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
            // 这里可以根据需要进行进一步的 JSON 数据转换操作
            // 例如，转换数据结构，或者添加新的键值对等
            // 这里简单返回转换后的数据
            String convertedJson = jsonObject.toString();
            // 返回转换后的 JSON 数据
            return Response.ok(convertedJson).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.BAD_REQUEST).entity({"error": "Invalid JSON input"}).build();
        }
    }

    // GET 方法，返回转换次数
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        return Response.ok({"count": counter.get()}).build();
    }

    // 私有方法，用于增加转换次数
    private void incrementCount() {
        counter.incrementAndGet();
    }
}
