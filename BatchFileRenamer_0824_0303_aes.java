// 代码生成时间: 2025-08-24 03:03:52
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rename")
public class BatchFileRenamer {

    @POST
    @Path("/batch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response renameFiles(List<String> oldNames, List<String> newNames) {
        if (oldNames.size() != newNames.size()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Old and new names lists must be of the same size.").build();
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < oldNames.size(); i++) {
            String oldName = oldNames.get(i);
            String newName = newNames.get(i);
            try {
                Path oldPath = Path.of(oldName);
                Path newPath = Path.of(newName);
                Files.move(oldPath, newPath);
                result.add("Successfully renamed '" + oldName + "' to '" + newName + "'");
            } catch (Exception e) {
                result.add("Error renaming '" + oldName + "' to '" + newName + "': " + e.getMessage());
            }
        }

        return Response.ok(result).build();
    }

    // Helper method to get a list of files from a directory
    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFiles(@PathParam("dir") String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Directory not found.").build();
        }

        return Response.ok(getFilesList(directoryPath)).build();
    }

    // Helper method to convert files list to JSON
    private List<String> getFilesList(String directoryPath) {
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        List<String> filesList = new ArrayList<>();
        for (File file : files) {
            filesList.add(file.getName());
        }
        return filesList;
    }
}
