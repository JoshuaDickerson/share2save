package com.example.share2save.worker;

import com.example.share2save.model.Bookmark;
import com.example.share2save.model.BookmarkResponseObject;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by josh on 3/16/14.
 */
public class BookmarkMapper {
    public static List<Bookmark> mapResponseToBookmarkList(BookmarkResponseObject responseObject){
        List<Bookmark> bookmarks = newArrayList();

        for(Map<String, String> url : responseObject.getBookmarks()) {
            Bookmark bookmark = new Bookmark();
            bookmark.setUrl(url.get("url"));
            bookmark.setTitle(url.get("title"));
            bookmark.setTags(responseObject.getTags());
            bookmarks.add(bookmark);
        }

        return bookmarks;
    }

}
