// 代码生成时间: 2025-09-05 02:58:02
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptionService {

    private static final String ALGORITHM = "AES";
    private static final String CHARSET = "UTF-8";

    // Generate a secret key for AES encryption
    private static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(128); // Use 128-bit AES key
        return keyGenerator.generateKey();
    }

    // Encrypt a password using AES encryption
    public static String encryptPassword(String password) throws Exception {
        SecretKey key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes(CHARSET));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a password using AES encryption
    public static String decryptPassword(String encryptedPassword) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        SecretKey key = new SecretKeySpec(decodedBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(decodedBytes), CHARSET);
    }

    public static void main(String[] args) {
        try {
            String password = "mySecretPassword";
            String encryptedPassword = encryptPassword(password);
            System.out.println("Encrypted Password: " + encryptedPassword);

            String decryptedPassword = decryptPassword(encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            System.err.println("Error during encryption/decryption: " + e.getMessage());
        }
    }
}
