// 代码生成时间: 2025-09-16 22:52:57
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Path("/decompress")
public class FileDecompressor {

    // 解压ZIP文件到指定目录
    @GET
    @Path("/zip")
    @Produces(MediaType.TEXT_PLAIN)
    public Response decompressZipFile(@QueryParam("source") String sourcePath, @QueryParam("destination") String destinationPath) {
        try {
            File sourceFile = new File(sourcePath);
            File destinationDir = new File(destinationPath);

            // 确保源文件存在且为文件
            if (!sourceFile.exists() || !sourceFile.isFile()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Source file not found.").build();
            }

            // 确保目标目录存在
            if (!destinationDir.exists() && !destinationDir.mkdirs()) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create destination directory.").build();
            }

            unzipFile(sourceFile, destinationDir);
            return Response.ok("File decompressed successfully.").build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error decompressing file: " + e.getMessage()).build();
        }
    }

    // 实际解压ZIP文件的方法
    private void unzipFile(File zipFile, File destDir) throws IOException {
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry entry = zipIn.getNextEntry();
        while (entry != null) {
            File filePath = new File(destDir, entry.getName());
            if (!entry.isDirectory()) {
                extractFile(zipIn, filePath);
            } else {
                filePath.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    // 从ZIP输入流中提取文件
    private void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}
