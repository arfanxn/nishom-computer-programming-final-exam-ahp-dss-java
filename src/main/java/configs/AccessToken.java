/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jasypt.util.text.AES256TextEncryptor;

/**
 *
 * @author arfanxn
 */
public class AccessToken {

    private AES256TextEncryptor encryptor;
    private String filename;

    // Static variable reference of instance type Singleton
    private static AccessToken instance;

    // Static method to create instance of Singleton class
    public static synchronized AccessToken getInstance() {
        instance = instance == null ? new AccessToken() : instance;
        return instance;
    }

    // Singleton private constructor 
    private AccessToken() {
        this.encryptor = new AES256TextEncryptor();
    }

    public AccessToken setEncryptorPassword(String password) {
        this.encryptor.setPassword(password);
        return this;
    }

    public String getFilename() {
        return this.filename;
    }

    public AccessToken setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public AES256TextEncryptor getEncryptor() {
        return this.encryptor;
    }

    public void set(String token) throws IOException {
        String encryptedToken = encryptor.encrypt(token);

        // Write the encrypted token to the file
        Files.write(Paths.get(this.getFilename()), encryptedToken.getBytes());
    }

    public String get() throws IOException {
        // Read the encrypted token from the file
        String encryptedToken = new String(Files.readAllBytes(Paths.get(this.getFilename())));

        return encryptor.decrypt(encryptedToken);
    }

    public void remove() throws IOException {
        Files.delete(Paths.get(this.getFilename()));
    }
}
