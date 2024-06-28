/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

/**
 *
 * @author arfanxn
 */
public class Database {

    // Static variable reference of instance type Singleton
    private static Database instance;

    // Static method to create instance of Singleton class
    public static synchronized Database getInstance() {
        instance = instance == null ? new Database() : instance;
        return instance;
    }

    private String jdbcUrlString;
    private String connection, host, port, database, driver;
    private String username, password;

    // Singleton private constructor 
    private Database() {
    }

    private void setJdbcUrlString(String urlString) {
        this.jdbcUrlString = urlString;
    }

    public String getJdbcUrlString() {
        // output example: jdbc:mysql://localhost:3306/e-parking?user=root&password=&serverTimezone=UTC
        this.jdbcUrlString = String.format("%s:%s://%s:%s/%s?user=%s&password=%s&serverTimezone=UTC",
                this.getDriver(),
                this.getConnection(),
                this.getHost(),
                this.getPort(),
                this.getDatabase(),
                this.getUsername(),
                this.getPassword()
        );
        return this.jdbcUrlString;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
