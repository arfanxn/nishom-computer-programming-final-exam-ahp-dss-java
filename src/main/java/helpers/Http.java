package helpers;

import configs.AccessToken;
import java.io.IOException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author arfanxn
 */
public class Http {

    public static Request request(String uri, String method) throws IOException {
        Request request = null;

        switch (method.toLowerCase()) {
            case "post":
                request = Request.Post(uri);
                break;
            case "put":
                request = Request.Put(uri);
                break;
            case "delete":
                request = Request.Delete(uri);
                break;
            case "get":
            default:
                request = Request.Get(uri);
                break;
        }

        request = request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        try {
            var tokenStr = AccessToken.getInstance().get();
            request = request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokenStr);
        } catch (IOException e) {
        }

        return request;
    }

}
