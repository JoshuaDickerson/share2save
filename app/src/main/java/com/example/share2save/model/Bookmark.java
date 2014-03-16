package com.example.share2save.model;
import java.io.Serializable;
import java.util.Set;

public class Bookmark implements Serializable{
    private Long id;
    Set<Tag> tags;
    User user;
    String url;
    String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
