// 代码生成时间: 2025-08-20 21:02:53
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/rename")
public class BatchFileRenamer {

    // Endpoint to handle file batch renaming process
    @POST
    @Path("/batch")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response renameFiles(
        @FormDataParam("files") List<File> fileList,
        @FormDataParam("filePrefix") String filePrefix,
        @FormDataParam("fileExtension") String fileExtension) {
        try {
            List<String> successMessages = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();

            for (File file : fileList) {
                // Create a new file name with the given prefix and extension
                String newFileName = filePrefix + file.getName().substring(
                    file.getName().lastIndexOf("."));
                Path sourcePath = Paths.get(file.getAbsolutePath());
                Path targetPath = Paths.get(file.getAbsolutePath().replace(
                    file.getName(), newFileName));

                // Rename the file
                Files.move(sourcePath, targetPath);
                successMessages.add("File renamed: " + file.getName());
            }

            if (successMessages.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity(
                    "No files were renamed.").build();
            } else {
                return Response.status(Response.Status.OK).entity(
                    "Files renamed successfully: " + String.join(", ", successMessages)).build();
            }
        } catch (IOException e) {
            errorMessages.add("Error renaming files: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(
                errorMessages).build();
        }
    }

    // Helper method to get file name without extension
    private String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }
}
