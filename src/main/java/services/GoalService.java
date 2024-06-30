/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import configs.URI;
import exceptions.ResponseException;
import helpers.Http;
import java.io.IOException;
import java.util.ArrayList;
import models.User;
import responses.ResponseBody;
import utilities.Context;
import java.util.List;
import models.Goal;
import org.apache.http.util.EntityUtils;
import responses.Pagination;

/**
 *
 * @author arfanxn
 */
public class GoalService {
    
      public Context index(Context ctx) throws ResponseException {
        try {
            var response = Http.request(URI.api("/goals"), "get")
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }

            var body = new ResponseBody(response.getEntity());

            TypeReference<Pagination<Goal>> typeRef = new TypeReference<Pagination<Goal>>() {
            };
            Pagination<Goal> goalsPagination = body.get("goals", typeRef);
            
            ctx.put("message", body.getMessage());
            ctx.put("goals", goalsPagination);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
}
