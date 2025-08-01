// 代码生成时间: 2025-08-01 10:56:01
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/analyze")
public class TextFileAnalyzer {

    // Logger for error handling and debugging
    private static final Logger LOGGER = Logger.getLogger(TextFileAnalyzer.class.getName());

    /**
     * Analyze the content of a text file and return statistics.
     *
     * @param filePath The path to the text file to analyze.
     * @return A JSON response with the analysis results.
     */
    @GET
    public Response analyzeTextFile(@QueryParam("filePath") String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("File path is required.").build();
        }

        try {
            int charCount = 0;
            int wordCount = 0;
            int lineCount = 0;

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                String[] words = line.split("[^a-zA-Z]+");
                wordCount += words.length;
            }
            reader.close();

            // Build the response with the results
            String response = String.format("{"charCount": %d, "wordCount": %d, "lineCount": %d}",
                    charCount, wordCount, lineCount);
            return Response.ok(response).build();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error reading file: " + filePath, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error reading file.").build();
        }
    }
}
