// 代码生成时间: 2025-10-05 03:41:21
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

// 使用Jersey框架创建一个名为MockDataGenerator的RESTful服务
@Path("/mockdata")
public class MockDataGenerator {

    // 使用GET请求生成随机字符串
    @GET
    @Path("/generateString")
    @Produces(MediaType.TEXT_PLAIN)
    public Response generateRandomString() {
        try {
            // 生成随机字符串
            String randomString = generateRandomAlphanumeric(10);
            return Response.ok(randomString).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating random string: " + e.getMessage()).build();
        }
    }

    // 使用GET请求生成随机整数
    @GET
    @Path("/generateInt")
    @Produces(MediaType.TEXT_PLAIN)
    public Response generateRandomInt() {
        try {
            // 生成随机整数
            int randomInt = new Random().nextInt(Integer.MAX_VALUE);
            return Response.ok(String.valueOf(randomInt)).build();
        } catch (Exception e) {
            // 错误处理
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating random integer: " + e.getMessage()).build();
        }
    }

    // 私有方法：生成随机字母数字字符串
    private String generateRandomAlphanumeric(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < length) {
            int charType = random.nextInt(3);
            switch (charType) {
                case 0: // 随机小写字母
                    builder.append((char) ('a' + random.nextInt(26)));
                    break;
                case 1: // 随机大写字母
                    builder.append((char) ('A' + random.nextInt(26)));
                    break;
                case 2: // 随机数字
                    builder.append(random.nextInt(10));
                    break;
            }
        }
        return builder.toString();
    }
}
