package com.example.share2save.model;

import com.google.gson.annotations.Expose;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * Created by josh on 3/16/14.
 */
public class BookmarkResponseObject extends ApiResponseObject {
    @Expose Set<String> tags;
    @Expose List<Map<String, String>> bookmarks;

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = newHashSet(tags);
    }

    public List<Map<String, String>> getBookmarks(){
        return this.bookmarks;
    }

    public void setBookmarks(List<Map<String, String>> bookmarks){
        this.bookmarks = bookmarks;
    }

}
