// 代码生成时间: 2025-09-07 03:39:56
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.net.MalformedURLException;

@Path("/url")
public class UrlValidatorService {

    @GET
    @Path("/validate")
    public Response validateUrl(@QueryParam("url") String urlString) {
        try {
            // 尝试解析URL，如果失败，则URL格式不正确
            URL url = new URL(urlString);
            // 可以在这里添加检查URL是否可达的逻辑，例如使用HttpURLConnection

            // 如果URL正确解析，则返回成功响应
            return Response.ok("URL is valid").build();
        } catch (MalformedURLException e) {
            // 如果URL格式不正确，则返回错误响应
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid URL format").build();
        } catch (Exception e) {
            // 捕捉其他可能的异常，返回服务器错误响应
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
        }
    }
}
