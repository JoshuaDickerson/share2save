package com.example.share2save.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by josh on 12/22/13.
 * Every object we intend to turn into JSON should implement Serializable
 */
public class Tag implements Serializable{
    private Long id;
    private String tag;
    private Set<Url> url;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Url> getUrl() {
        return this.url;
    }

    public void setUrl(Set<Url> url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Tag tag = (Tag) o;

        if (!id.equals(tag.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
