import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class AuthorizationCodeGenerator {

    public static String generateAuthorizationCode(Long parameter) {
        String message = parameter.toString();
        byte[] salt = new byte[16];
        new Random().nextBytes(salt);
        byte[] combined = new byte[message.getBytes().length + salt.length];
        ByteBuffer buff = ByteBuffer.wrap(combined);
        buff.put(message.getBytes());
        buff.put(salt);
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(combined);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String authorizationCode = Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 32);
        return authorizationCode;
    }

    public static void main(String[] args) {
        Long parameter = 12345L;
        String authorizationCode = generateAuthorizationCode(parameter);
        System.out.println("Authorization code: " + authorizationCode);
    }

}