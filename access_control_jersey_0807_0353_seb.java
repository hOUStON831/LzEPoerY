// 代码生成时间: 2025-08-07 03:53:17
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.server.oauth1.signature.OAuth1Signature;
import org.glassfish.jersey.server.oauth1.signature.OAuth1SignatureException;
import org.glassfish.jersey.server.oauth1.signature.OAuth1SignatureMethod;
import org.glassfish.jersey.server.oauth1.signature.OAuth1SignatureService;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Path("/access")
public class AccessControlService {

    private static final Logger LOGGER = Logger.getLogger(AccessControlService.class.getName());

    private final OAuth1SignatureService oAuthService = new OAuth1SignatureService();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkAccess() {
        try {
            // 模拟验证过程，实际应用中可以使用数据库或其他存储机制验证
            Map<String, String> credentials = new HashMap<>();
            credentials.put("consumerKey", "your_consumer_key");
            credentials.put("consumerSecret", "your_consumer_secret");
            credentials.put("token", "your_access_token");
            credentials.put("tokenSecret", "your_access_token_secret");

            OAuth1Signature signature = oAuthService.createSignature(credentials);
            signature.sign("GET", "/access", new HashMap<>());

            // 如果签名验证通过，返回成功信息
            return Response.ok("Access granted").build();

        } catch (OAuth1SignatureException e) {
            LOGGER.severe("Access denied: " + e.getMessage());
            // 签名验证失败或发生异常，返回错误信息
            return Response.status(Response.Status.UNAUTHORIZED).entity("Access denied").build();
        }
    }

    public static class AppConfig extends ResourceConfig {
        public AppConfig() {
            packages("your.package.name");
            register(FreemarkerMvcFeature.class);
        }
    }
}
