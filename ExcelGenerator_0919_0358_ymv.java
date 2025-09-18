// 代码生成时间: 2025-09-19 03:58:56
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Path("/excel")
public class ExcelGenerator {

    // 创建一个新的Excel工作簿
    private Workbook createWorkbook() {
        return new XSSFWorkbook();
    }

    // POST方法用于接收请求数据并生成Excel文件
    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateExcel(@FormDataParam("data") InputStream dataStream) {
        try {
            // 创建新的工作簿
            Workbook workbook = createWorkbook();
            // 假设我们有一个方法来处理流数据并填充到工作簿中
            // 这里我们只是简单地添加一个空的工作表
            workbook.createSheet("GeneratedSheet");

            // 将工作簿转换为输出流并发送
            InputStream excelStream = toExcelStream(workbook);
            return Response.ok(excelStream, MediaType.APPLICATION_OCTET_STREAM).header("Content-Disposition", "attachment; filename=generated.xlsx").build();
        } catch (IOException e) {
            // 错误处理，返回内部服务器错误
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error generating Excel file.").build();
        }
    }

    // 将Workbook转换为InputStream
    private InputStream toExcelStream(Workbook workbook) throws IOException {
        try (OutputStream excelOutputStream = new java.io.ByteArrayOutputStream()) {
            workbook.write(excelOutputStream);
            return new java.io.ByteArrayInputStream(excelOutputStream.toByteArray());
        } finally {
            workbook.close();
        }
    }
}
