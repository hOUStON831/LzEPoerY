// 代码生成时间: 2025-09-11 09:41:25
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Base64;

@Path("/password")
public class PasswordEncryptionDecryptionService {

    // 使用AES算法进行加密解密的密钥
    private static final String ALGORITHM = "AES";
    private static final String KEY = "yourSecretKeyHere";

    @GET
    @Path("/encrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String encrypt(@QueryParam("password") String password) throws Exception {
        return encryptDecrypt(password, true);
    }

    @GET
    @Path("/decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String decrypt(@QueryParam("password") String password) throws Exception {
        return encryptDecrypt(password, false);
    }

    // 加密解密方法
    private String encryptDecrypt(String password, boolean isEncrypt) throws Exception {
        // 将字符串形式的密钥转换为SecretKey对象
        SecretKey secretKey = new SecretKeySpec(KEY.getBytes(), ALGORITHM);

        // 创建Cipher对象，并指定AES加密模式为CBC
        Cipher cipher = Cipher.getInstance(ALGORITHM + "/CBC/PKCS5Padding");

        // 初始化Cipher对象为加密或解密模式
        cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, secretKey);

        // 对密码进行加密或解密
        byte[] result = cipher.doFinal(password.getBytes());

        // 对结果进行Base64编码，以便在URL中传输
        return Base64.getEncoder().encodeToString(result);
    }
}
