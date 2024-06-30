/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import configs.URI;
import exceptions.ResponseException;
import helpers.Http;
import java.io.IOException;
import org.json.JSONException;
import responses.ResponseBody;
import utilities.Context;
import models.User;
import java.util.Map;
import org.apache.http.entity.ContentType;

/**
 *
 * @author arfanxn
 */
public class UserService {
    
    public UserService() {
    }
    
    public Context showSelf(Context ctx) throws ResponseException {
        try {
            var response = Http.request(URI.api("/users/self"), "get")
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }
            
            var body = new ResponseBody(response.getEntity());
            User user = body.get("user", User.class);
            ctx.put("user", user);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
    
    public Context update (Context ctx) throws  ResponseException {
        try {
            ObjectMapper om = new ObjectMapper();
            Map<String, String> form = ctx.<Map>get("form", Map.class);
            String userId = String.valueOf(form.get("id"));
            var jsonStr = om.writeValueAsString(form);
            
            var response = Http.request(URI.api("/users/" + userId), "put")
                    .bodyString(jsonStr, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }
            
            var body = new ResponseBody(response.getEntity());
            ctx.put("message", body.getMessage());

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
}
