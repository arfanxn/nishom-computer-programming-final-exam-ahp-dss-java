/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import configs.ENV;
import exceptions.ResponseException;
import helpers.Http;
import java.io.IOException;
import java.net.URISyntaxException;
import responses.ResponseBody;
import utilities.Context;
import models.Goal;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import responses.Pagination;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author arfanxn
 */
public class GoalService {

    public Context index(Context ctx) throws ResponseException {
        try {
            Integer page = ctx.<Integer>get("page");

            String keyword = ctx.<String>get("keyword");
            keyword = keyword != null ? keyword : "";
            
            String uriStr = ENV.get("API_URL") + "/goals";

            URIBuilder uriBuilder = null;
            try {
                uriBuilder = (new URIBuilder(uriStr))
                        .addParameter("page", String.valueOf(page))
                        .addParameter("keyword", keyword);
                uriStr = uriBuilder.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            var response = Http
                    .request(uriStr, "get")
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

    public Context add(Context ctx) throws ResponseException {
        try {
            String uriStr = ENV.get("API_URL") + "/goals";
            var response = Http.request(uriStr, "post")
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
    
    public Context show(Context ctx) throws ResponseException {
        try {
            String uriStr = ENV.get("API_URL") + "/goals/" + ctx.get("goal_id");
            var response = Http.request(uriStr, "get")
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }

            var body = new ResponseBody(response.getEntity());
            Goal goal = body.get("goal", new TypeReference<Goal>() {
            });

            ctx.put("message", body.getMessage());
            ctx.put("goal", goal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
    public Context rankingization(Context ctx) throws ResponseException {
        try {
            String uriStr = ENV.get("API_URL") + "/goals/" + ctx.get("goal_id") + "/rankingization";
            var response = Http.request(uriStr, "get")
                    .execute()
                    .returnResponse();
            var statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                throw new ResponseException(response);
            }

            var body = new ResponseBody(response.getEntity());
            List<Map<String, String>> goalRankingization = body.get("rankings", new TypeReference<List>() {
            });

            ctx.put("message", body.getMessage());
            ctx.put("rankings", goalRankingization);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ctx;
    }
    
    public Context update(Context ctx) throws ResponseException {
        try {
            ObjectMapper om = new ObjectMapper();
            var bodyStr = om.writeValueAsString(ctx.get("body"));
            
            String goalId = om.readTree(bodyStr).get("id").asText();
            
            var response = Http
                  .request(
                            ENV.get("API_URL") + "/goals/" + goalId,
                            "put")
                  .bodyString(bodyStr, ContentType.APPLICATION_JSON)
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
