// 代码生成时间: 2025-08-09 00:59:28
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Path("/analyzer")
public class TextFileAnalyzer {

    // Endpoint to analyze text file content
    @POST
    @Path("/analyze")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response analyzeTextFile(String filePath) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Perform analysis on the file content, e.g., count the number of lines
            int lineCount = lines.size();

            // Return analysis result as JSON
            return Response
                    .ok()
                    .entity("{"lineCount": 