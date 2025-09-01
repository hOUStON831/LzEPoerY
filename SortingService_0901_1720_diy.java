// 代码生成时间: 2025-09-01 17:20:39
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
# 扩展功能模块
import java.util.Arrays;
# TODO: 优化性能
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
# 优化算法效率

/**
 * SortingService provides RESTful services to perform sorting on an array of integers.
 */
@Path("/sort")
public class SortingService {

    /**
     * Sorts an array of integers using the bubble sort algorithm and returns the sorted list.
# TODO: 优化性能
     *
     * @param array The array of integers to sort.
     * @return A list of sorted integers.
# TODO: 优化性能
     */
# 改进用户体验
    @GET
    @Path("/bubble")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> bubbleSort(@QueryParam("array") List<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
# 扩展功能模块

        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
# NOTE: 重要实现细节
                    // Swap temp and array[i]
                    int temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
        return array;
# TODO: 优化性能
    }
# 添加错误处理

    /**
     * Sorts an array of integers using the quick sort algorithm and returns the sorted list.
     *
     * @param array The array of integers to sort.
# 添加错误处理
     * @return A list of sorted integers.
     */
# TODO: 优化性能
    @GET
    @Path("/quick")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> quickSort(@QueryParam("array") List<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException("Array cannot be null or empty");
# 扩展功能模块
        }

        return quickSort(array, 0, array.size() - 1);
    }

    private List<Integer> quickSort(List<Integer> array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
# 改进用户体验
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
# 扩展功能模块
        }
        return array;
    }

    private int partition(List<Integer> array, int low, int high) {
        int pivot = array.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array.get(j) < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(List<Integer> array, int i, int j) {
# 扩展功能模块
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
# 改进用户体验
}
