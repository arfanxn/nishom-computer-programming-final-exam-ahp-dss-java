/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

import org.apache.http.HttpResponse;
import responses.ResponseBody;


/**
 *
 * @author arfanxn
 */
public class ResponseException extends Exception {

    private HttpResponse response;
    private ResponseBody body;
    private int statusCode;

    /**
     * Creates a new instance of <code>ResponseException</code> without detail
     * message.
     *
     * @param response
     */
    public ResponseException(HttpResponse response) {
        this.response = response;
        this.body = new ResponseBody(response.getEntity());
        this.statusCode = response.getStatusLine().getStatusCode();
    }

    /**
     * Constructs an instance of <code>ResponseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ResponseException(String msg) {
        super(msg);
    }
    
    public HttpResponse getResponse() {
        return this.response;
    }
    
    public int getStatusCode() {
        return this.statusCode;
    }

    public ResponseBody getBody() {
        return body;
    }
    
    /**
     *
     * @return String
     */
    @Override
    public String getMessage() {
        return this.body.getMessage();
    }

}
