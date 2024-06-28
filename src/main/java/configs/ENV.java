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
    
    public ENV loadAndConfigure() {
        this.dotenv = Dotenv.configure().load();
        this.configureDatabase();
        return this;
    }
    
    public ENV configureDatabase () {
        Database databaseConfig = Database.getInstance();
        databaseConfig.setDriver(this.dotenv.get("DB_DRIVER", "jdbc"));
        databaseConfig.setConnection(this.dotenv.get("DB_CONNECTION", "mysql"));
        databaseConfig.setHost(this.dotenv.get("DB_HOST", "127.0.0.1"));
        databaseConfig.setPort(this.dotenv.get("DB_PORT", "3306"));
        databaseConfig.setDatabase(this.dotenv.get("DB_DATABASE", "e-parking")); // database name
        databaseConfig.setUsername(this.dotenv.get("DB_USERNAME", "root")); 
        databaseConfig.setPassword(this.dotenv.get("DB_PASSWORD", "")); // database name

        return this;

    }

}
