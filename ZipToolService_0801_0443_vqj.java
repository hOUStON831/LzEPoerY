// 代码生成时间: 2025-08-01 04:43:25
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Path("/zip")
public class ZipToolService {

    @POST
    @Path("/unzip")
    @Produces(MediaType.TEXT_PLAIN)
    public Response unzipFile(@FormDataParam("file") InputStream uploadedInputStream) {
        try {
            // Define the destination directory
            Path destinationPath = Paths.get("./unzipped");
            if (!Files.exists(destinationPath)) {
                Files.createDirectories(destinationPath);
            }

            // Unzip the file
            unzip(uploadedInputStream, destinationPath);

            // Return a success message
            return Response.status(Response.Status.OK).entity("File unzipped successfully.").build();
        } catch (IOException e) {
            // Return an error message if an exception occurs
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error unzipping file: " + e.getMessage()).build();
        }
    }

    private void unzip(InputStream zipInputStream, Path destinationPath) throws IOException {
        // Create a new ZipInputStream
        ZipInputStream zipIn = new ZipInputStream(zipInputStream);
        ZipEntry entry = zipIn.getNextEntry();
        // Iterate over the entries in the zip file
        while (entry != null) {
            String filePath = destinationPath + "/" + entry.getName();
            if (!entry.isDirectory()) {
                // If the entry is a file, extract it
                extractFile(zipIn, filePath);
            } else {
                // If the entry is a directory, create it
                Files.createDirectories(Paths.get(filePath));
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        // Create a buffer to hold the data
        byte[] buffer = new byte[1024];
        try (OutputStream out = Files.newOutputStream(Paths.get(filePath))) {
            int len;
            // Read and write data until the end of the file is reached
            while ((len = zipIn.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
    }
}
