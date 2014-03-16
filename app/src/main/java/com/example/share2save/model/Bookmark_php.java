package com.example.share2save.model;

import android.content.Intent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by josh on 3/9/14.
 */
public class Bookmark_php {
    private Integer id;
    private URL url;
    private String title;
    private Set<String> tags = newHashSet();

    public Bookmark_php(){}

    public Bookmark_php(URL url, String title, Set<String> tags){
        this.url = url;
        this.title = title;
        if(tags != null && !tags.isEmpty()) {
            this.tags.addAll(tags);
        }
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUrl(URL url){
        this.url = url;
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public void setTags(Set<String> tags){
        this.tags = tags;
    }

    public void addTag(String tag){
        this.tags.add(tag);
    }

    public void setTitle(String title){
        this.title = title;
    }
}