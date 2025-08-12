// 代码生成时间: 2025-08-12 13:49:47
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * Service class to handle sorting operations.
 */
@Path("/sort")
public class SortService {

    /**
     * Get method to sort numbers using a sorting algorithm.
     * @param numbers List of numbers to be sorted.
     * @return Sorted list of numbers.
     */
    @GET
    @Path("/sortNumbers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> sortNumbers(@QueryParam("numbers") List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            // Handle the case where numbers list is null or empty.
            return Collections.emptyList();
        }
        try {
            // Sort the list of numbers using the built-in sort method.
            Collections.sort(numbers);
            return numbers;
        } catch (Exception e) {
            // Handle any unexpected exceptions that may occur during sorting.
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
