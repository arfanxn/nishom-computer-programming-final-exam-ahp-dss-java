/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import configs.URI;
import exceptions.ResponseException;
import helpers.Http;
import java.io.IOException;
import responses.ResponseBody;
import utilities.Context;
import models.User;
import java.util.Map;
import org.apache.http.entity.ContentType;
import requests.UpdateUserRequest;


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
            User user = body.get("user", new TypeReference<User>() {
            });
            ctx.put("message", body.getMessage());
            ctx.put("user", user);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
    
    public Context update (Context ctx) throws  ResponseException {
        try {
            ObjectMapper om = new ObjectMapper();
            UpdateUserRequest reqBody = ctx.<UpdateUserRequest>get("body");
            String userId = String.valueOf(reqBody.getId());
            var reqBodyStr = om.writeValueAsString(reqBody);
            
            var response = Http.request(URI.api("/users/" + userId), "put")
                    .bodyString(reqBodyStr, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }
            
            var body = new ResponseBody(response.getEntity());
            ctx.put("message", body.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
}
