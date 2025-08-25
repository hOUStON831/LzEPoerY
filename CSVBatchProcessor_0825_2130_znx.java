// 代码生成时间: 2025-08-25 21:30:04
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// CSV文件批量处理器
@Path("/batch")
public class CSVBatchProcessor {

    // 文件上传和处理方法
    @POST
    @Path("/processCSV")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response processCSVFiles(
            @FormDataParam("file") List<FormDataContentDisposition> meta,
            @FormDataParam("file") List<InputStream> inputStreams) {
        try {
            // 检查文件列表是否为空
            if (inputStreams.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("No files were uploaded").build();
            }

            // 处理每个上传的文件
            for (int i = 0; i < inputStreams.size(); i++) {
                InputStream inputStream = inputStreams.get(i);
                FormDataContentDisposition contentDisposition = meta.get(i);
                String fileName = contentDisposition.getFileName();

                // 保存文件到磁盘
                Path filePath = saveFile(inputStream, fileName);

                // 处理CSV文件（示例中仅打印文件路径）
                processCSVFile(filePath);
            }

            // 返回成功响应
            return Response.ok("CSV files processed successfully").build();
        } catch (IOException e) {
            // 返回错误响应
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing CSV files: " + e.getMessage()).build();
        }
    }

    // 保存文件到磁盘
    private Path saveFile(InputStream inputStream, String fileName) throws IOException {
        Path filePath = Paths.get("uploads/" + fileName);
        Files.copy(inputStream, filePath);
        return filePath;
    }

    // 处理CSV文件（这里可以根据需要实现具体的处理逻辑）
    private void processCSVFile(Path filePath) {
        // 示例：仅打印文件路径
        System.out.println("Processing file: " + filePath);

        // TODO: 实现CSV文件处理逻辑
    }
}
