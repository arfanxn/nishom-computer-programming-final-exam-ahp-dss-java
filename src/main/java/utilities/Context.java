/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.HashMap;

/**
 *
 * @author arfanxn
 */
public class Context extends HashMap<String, Object> {

    // You can add constructors or other methods as needed
    @Override
    public Object put(String key, Object value) {
        // Optional logic before adding to the map (e.g., null checks)
        return super.put(key, value);
    }

    /**
     *
     * @return utilities.Context
     */
    public static Context instantiate() {
        return new Context();
    }

    // You can add optional methods for type safety:
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        return (T) super.get(key);
    }
    
    // Pull will retrieve and delete after retrieval
    public <T> T pull(String key, Class<T> type) {
        var value = this.get(key, type);
        this.remove(key);
        return value;
    }

    public Context only(String... keys) {
        Context ctx = new Context();
        for (String key : keys) {
            Object value = this.get(key);
            if (value != null) {
                ctx.put(key, value);
            }
        }
        return ctx;
    }
    
    public boolean has(String key) {
        return this.containsKey(key);
    }

}
