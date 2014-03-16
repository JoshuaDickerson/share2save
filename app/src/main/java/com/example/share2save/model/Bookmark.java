package com.example.share2save.model;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class Bookmark implements Serializable{
    private Long id;
    @Expose private String url;
    @Expose private String title;
    @Expose private Set<String> tags;

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

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void setId(Long id){
        this.id = id;
    }
}
