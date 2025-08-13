// 代码生成时间: 2025-08-13 09:04:37
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/process-manager")
public class ProcessManager {
    
    /**
     * Retrieves a list of running processes from the system.
     * 
     * @return A JSON response containing the list of processes.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRunningProcesses() {
        List<String> processes = new ArrayList<>();
        try {
            // Execute the command to list running processes
            ProcessBuilder processBuilder = new ProcessBuilder("ps", "-ef");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            
            // Read the output from the command
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    processes.add(line);
                }
            }
            
            // Wait for the process to terminate
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                              .entity("Failed to retrieve processes.")
                              .build();
            }
        } catch (IOException | InterruptedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                          .entity("Error processing request: " + e.getMessage())
                          .build();
        }
        
        // Return the list of processes as a JSON response
        return Response.ok(processes.toString()).build();
    }
}