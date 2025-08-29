// 代码生成时间: 2025-08-29 18:12:18
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Path("/webcontent")
public class WebContentGrabber {

    // 定义获取网页内容的API路径
    @Path("/grab")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response grabWebContent() {
        try {
            // 配置URL和连接对象
            URL url = new URL("https://example.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                // 如果响应码不是200，返回错误信息
                return Response.status(responseCode).entity("Failed to get the webpage content.").build();
            }

            // 读取网页内容
            Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.name());
            StringBuilder content = new StringBuilder();
            while (scanner.hasNext()) {
                content.append(scanner.nextLine()).append("
");
            }
            scanner.close();

            // 返回网页内容
            return Response.ok(content.toString(), MediaType.TEXT_HTML_TYPE).build();

        } catch (IOException e) {
            // 返回错误信息
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred while fetching the web content: " + e.getMessage()).build();
        }
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        // 这里可以使用一个简单的Jersey server来测试这个资源
    }
}
