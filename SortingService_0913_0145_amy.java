// 代码生成时间: 2025-09-13 01:45:43
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// 使用JERSEY框架创建REST服务
@Path("/sort")
public class SortingService {

    // 使用GET方法返回排序后的结果
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getSortedNumbers() {
        // 初始化一个测试数组
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);

        try {
            // 使用Arrays.sort()方法进行排序
            numbers.sort(Comparator.naturalOrder());
            return numbers;
        } catch (Exception e) {
            // 错误处理
            // 将错误信息返回给客户端
            return Arrays.asList("Error: " + e.getMessage());
        }
    }
}
