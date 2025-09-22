// 代码生成时间: 2025-09-23 01:00:20
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * SortingService provides a RESTful API to sort integers.
 */
@Path("/sort")
public class SortingService {

    /**
     * Sorts a list of integers using the natural ordering.
     *
     * @param numbers A comma-separated string of integers to sort.
     * @return A JSON string of sorted integers.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sortNumbers(@QueryParam("numbers") String numbers) {
        try {
            if (numbers == null || numbers.isEmpty()) {
                throw new IllegalArgumentException("No numbers provided for sorting.");
            }

            // Convert the input string to a list of integers.
            List<Integer> numbersList = Arrays.asList(numbers.split(",")).stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            // Sort the list using the natural ordering.
            Collections.sort(numbersList);

            // Convert the sorted list back to a comma-separated string.
            return Arrays.toString(numbersList.toArray()).replaceAll("\[|\]|", "").replace(", ", ",").trim();
        } catch (Exception e) {
            // Handle any exceptions that occur during sorting.
            return "{"error": "Sort failed: "}" + e.getMessage();
        }
    }
}