/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import configs.AccessToken;
import configs.URI;
import org.apache.http.entity.ContentType;
import models.User;
import utilities.Context;
import responses.ResponseBody;
import exceptions.ResponseException;
import helpers.Http;

/**
 *
 * @author arfanxn
 */
public class AuthService {

    public AuthService() {

    }

    public Context login(Context ctx) throws ResponseException {
        try {
            ObjectMapper om = new ObjectMapper();
            var jsonStr = om.writeValueAsString(ctx.get("form"));

            var response = Http
                    .request(URI.api("/login"), "post")
                    .bodyString(jsonStr, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ResponseException(response);
            }

            var body = new ResponseBody(response.getEntity());
            var user = body.get("user", User.class);

            AccessToken.getInstance().set(user.getAccessToken());
            
            ctx.put("message", body.getMessage());
            ctx.put("user", user);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ctx;
    }

    public Context logout(Context ctx) throws ResponseException {
        try {
            var response = Http.request(URI.api("/logout"), "delete")
                    .execute()
                    .returnResponse();

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new ResponseException(response);
            }

            var body = new ResponseBody(response.getEntity());
            
            ctx.put("message", body.getMessage());

            AccessToken.getInstance().remove();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }

}
