// 代码生成时间: 2025-08-05 02:14:53
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

/**
 * 测试数据生成器，用于生成随机测试数据。
 */
@Path("/test-data")
public class TestDataGenerator {

    /**
     * 生成随机整数测试数据。
     *
     * @return 随机生成的整数。
     */
    @GET
    @Path("/integer")
    @Produces(MediaType.TEXT_PLAIN)
    public int generateRandomInteger() {
        Random random = new Random();
        return random.nextInt();
    }

    /**
     * 生成随机字符串测试数据。
     *
     * @return 随机生成的字符串。
     */
    @GET
    @Path("/string")
    @Produces(MediaType.TEXT_PLAIN)
    public String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    /**
     * 生成随机浮点数测试数据。
     *
     * @return 随机生成的浮点数。
     */
    @GET
    @Path("/double")
    @Produces(MediaType.TEXT_PLAIN)
    public double generateRandomDouble() {
        Random random = new Random();
        return random.nextDouble();
    }
}
