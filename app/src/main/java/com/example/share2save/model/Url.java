package com.example.share2save.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by josh on 12/22/13.
 */
public class Url implements Serializable{
    @Expose private Long id;
    @Expose private String url;
    @Expose private String title;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
