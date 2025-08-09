// 代码生成时间: 2025-08-09 11:26:02
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/image")
public class ImageResizer {

    // Adjusts the size of an image
    @POST
    @Path("/resize")
    @Produces(MediaType.TEXT_PLAIN)
    public Response resizeImage(@FormDataParam("image") byte[] imageBytes, @FormDataParam("newWidth") int newWidth, @FormDataParam("newHeight") int newHeight) {
        try {
            BufferedImage originalImage = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
            resizedImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            java.io.ByteArrayOutputStream bao = new java.io.ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", bao);
            byte[] resizeedImageBytes = bao.toByteArray();
            return Response.ok(resizeedImageBytes, MediaType.APPLICATION_OCTET_STREAM).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error resizing image").build();
        }
    }

    // Provides a list of images in a directory
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listImages(@PathParam("dir") String dirPath) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists() || !dir.isDirectory()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Directory not found").build();
            }
            String[] files = dir.list((file, s) -> s.toLowerCase().endsWith(".png"));
            return Response.ok(files).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error listing images").build();
        }
    }
}
