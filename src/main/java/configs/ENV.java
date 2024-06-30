/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author arfanxn
 */
public class ENV {

    private Dotenv dotenv;

    // Static variable reference of instance type Singleton
    private static ENV instance;

    // Static method to create instance of Singleton class
    public static synchronized ENV getInstance() {
        instance = instance == null ? new ENV() : instance;
        return instance;
    }

    // Singleton private constructor 
    private ENV() {
    }

    public ENV load() {
        this.dotenv = Dotenv.configure().load();
        return this;
    } 
    
    // get retrieves env by key
    public String get(String key) {
        return this.dotenv.get(key);
    }
    
    // should be called after load()
    public ENV configure() {
        this.configureAccessToken();
        return this;
    }

    public void configureAccessToken() {
        String password = this.get("ACCESS_TOKEN_PASSWORD");
        String filename = this.get("ACCESS_TOKEN_FILENAME");
        AccessToken.getInstance()
                .setEncryptorPassword(password)
                .setFilename(filename);
    }

}
