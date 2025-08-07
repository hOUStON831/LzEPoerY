// 代码生成时间: 2025-08-07 08:01:46
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/network")
public class NetworkStatusChecker {

    // 定义一个端口号常量，用于检查网络连接状态
    private static final int CONNECTION_TIMEOUT = 3000; // 3秒超时
    private static final int READ_TIMEOUT = 3000;      // 3秒读取超时

    /**
     * 检查指定URL的网络连接状态
     * @param url 需要检查的URL地址
     * @return 网络连接状态的响应
     */
    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkConnectionStatus(@QueryParam("url") String url) {
        if (url == null || url.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("URL parameter is missing or empty.").build();
        }

        try {
            URL targetUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setRequestMethod("HEAD"); // 仅请求头部信息，不下载内容

            int responseCode = connection.getResponseCode();
            connection.disconnect();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return Response.ok().entity("Connection to URL is successful.").build();
            } else {
                return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                        .entity("Connection to URL failed with status: " + responseCode).build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while checking the connection: " + e.getMessage()).build();
        }
    }
}
