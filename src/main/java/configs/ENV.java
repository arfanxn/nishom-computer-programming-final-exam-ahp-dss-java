/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

import utilities.AccessToken;
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
    public static String get(String key) {
        return ENV.getInstance().dotenv.get(key);
    }
    
    // should be called after load()
    public ENV configure() {
        return this;
    }

}
