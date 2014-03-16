package com.example.share2save.model.error;

import com.google.gson.Gson;
import org.apache.http.HttpStatus;

/**
 * Created by josh on 12/29/13.
 */
public class ErrorResponse {
    private final HttpStatus statusCode;
    private final String apiVersion;
    private final String description;

    public ErrorResponse(HttpStatus statusCode, String apiVersion, String description){
        this.statusCode = statusCode;
        this.apiVersion = apiVersion;
        this.description = description;
    }

    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
