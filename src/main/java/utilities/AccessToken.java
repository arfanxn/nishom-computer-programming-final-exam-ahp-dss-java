/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

;
import configs.ENV;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jasypt.util.text.AES256TextEncryptor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jasypt.util.text.AES256TextEncryptor;

/**
 *
 * @author arfanxn
 */


public class AccessToken {

    private static String getPassword() {
        return ENV.get("ACCESS_TOKEN_PASSWORD");
    }

    private static String getFilename() {
        return ENV.get("ACCESS_TOKEN_FILENAME");
    }

    private static AES256TextEncryptor getEncryptor() {
        var encryptor = new AES256TextEncryptor();
        encryptor.setPassword(AccessToken.getPassword());
        return encryptor;
    }

    public static void set(String token) throws IOException {
        var encryptor = AccessToken.getEncryptor();
        String encryptedToken = encryptor.encrypt(token);
        // Write the encrypted token to the file
        Files.write(Paths.get(AccessToken.getFilename()), encryptedToken.getBytes());
    }

    public static String get() throws IOException {
        var encryptor = AccessToken.getEncryptor();
        // Read the encrypted token from the file
        String encryptedToken = new String(Files.readAllBytes(Paths.get(AccessToken.getFilename())));

        return encryptor.decrypt(encryptedToken);
    }

    public static void remove() throws IOException {
        Files.delete(Paths.get(AccessToken.getFilename()));
    }
}
