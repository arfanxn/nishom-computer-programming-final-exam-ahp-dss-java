/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author arfanxn
 */
public class ResponseBody {

    private ObjectMapper objectMapper;
    private JsonNode jsonNode;

    private HttpEntity httpEntity;
    private String message;
    private Map<String, String> errors;

    public ResponseBody(HttpEntity httpEntity) {
        this.httpEntity = httpEntity;
        try {
            this.objectMapper = new ObjectMapper();
            this.jsonNode = objectMapper.readTree(EntityUtils.toString(httpEntity));

            JsonNode messageNode = jsonNode.get("message");
            if (messageNode != null) {
                this.message = messageNode.asText();
            }
            JsonNode errorsNode = jsonNode.get("errors");
            if (errorsNode != null) {
                this.errors = objectMapper.readValue(errorsNode.traverse(), HashMap.class);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public JsonNode get(String keyPath) {
        String[] keys = keyPath.split("\\.");
        JsonNode node = this.jsonNode.get(keys[0]);
        if (node == null) {
            return null;
        }

        for (int i = 1; i < keys.length; i++) {
            var key = keys[i];
            node = node.get(key);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    public <T1> T1 get(String keyPath, TypeReference<T1> typeRef) throws IOException {
        JsonNode node = this.get(keyPath);
        return this.objectMapper.readValue(node.traverse(), typeRef);
    }

    public HttpEntity getHttpEntity() {
        return httpEntity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
