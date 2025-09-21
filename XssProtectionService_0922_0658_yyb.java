// 代码生成时间: 2025-09-22 06:58:44
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.owasp.encoder.Encode;

@Path("/xss-protection")
public class XssProtectionService {

    // 此方法演示了如何防止XSS攻击
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String preventXssAttack() {
        try {
            String userInput = "<script>alert('XSS')</script>";
            // 使用OWASP的Encoder进行编码，防止XSS攻击
            String safeInput = Encode.forHtmlContent(userInput);
            // 返回安全的HTML内容
            return "<html><body>" + 
                   "<h1>XSS Protection</h1>" + 
                   "<p>Encoded Input: " + safeInput + "</p>" + 
                   "</body></html>";
        } catch (Exception e) {
            // 错误处理
            return "<html><body><h1>Error</h1><p>Internal Server Error</p></body></html>";
        }
    }
}
