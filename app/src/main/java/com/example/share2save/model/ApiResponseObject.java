package com.example.share2save.model;

import com.google.gson.annotations.Expose;

/**
 * Created by josh on 3/16/14.
 */
public abstract class ApiResponseObject {
    @Expose String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
