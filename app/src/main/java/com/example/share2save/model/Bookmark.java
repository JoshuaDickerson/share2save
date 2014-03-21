package com.example.share2save.model;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class Bookmark{
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Bookmark bookmark = (Bookmark) o;

        if (title != null ? !title.equals(bookmark.title) : bookmark.title != null) return false;
        if (url != null ? !url.equals(bookmark.url) : bookmark.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
