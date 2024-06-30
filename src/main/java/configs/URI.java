/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configs;

/**
 *
 * @author arfanxn
 */
public class URI {
    
    public static String api(String path) {
        var url = ENV.getInstance().get("API_URL");
        if (path == null) {
            return url;
        }
        return url + path;
    }
    
    public static String api() {
        return URI.api(null);
    }
    
}
