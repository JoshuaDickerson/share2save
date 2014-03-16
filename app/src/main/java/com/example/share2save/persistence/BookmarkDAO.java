package com.example.share2save.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.share2save.model.Bookmark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by josh on 3/9/14.
 */
public class BookmarkDAO extends SQLiteOpenHelper {
    // tutorial: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
    Logger log = LoggerFactory.getLogger(BookmarkDAO.class);

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sharetosave";

    private static final String TABLE_BOOKMARKS = "bookmarks";
    private static final String BOOKMARKS_ID = "id";
    private static final String BOOKMARKS_URL = "url";
    private static final String BOOKMARKS_TITLE = "title";

    private static final String TABLE_TAGS = "tags";
    private static final String TAGS_ID = "id";
    private static final String TAGS_TAG = "tag";
    private static final String TAGS_BOOKMARKS_ID = "fkBookmarkId";

    public BookmarkDAO(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    public BookmarkDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BookmarkDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKMARK = "CREATE TABLE " + TABLE_BOOKMARKS +
                "(" + BOOKMARKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BOOKMARKS_URL + " TEXT," +
                BOOKMARKS_TITLE + " TEXT" +
                ");";

        db.execSQL(CREATE_BOOKMARK);

        String CREATE_TAG = "CREATE TABLE " + TABLE_TAGS + " (" +
                TAGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TAGS_TAG + " TEXT, " +
                TAGS_BOOKMARKS_ID + " INTEGER," +
                "FOREIGN KEY(" + TAGS_BOOKMARKS_ID + ") REFERENCES " + TABLE_BOOKMARKS + "(" + BOOKMARKS_ID + ")" +
                ");";

        db.execSQL(CREATE_TAG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(db);
    }

    public List<Bookmark> getAllBookmarks() {
        String select = "SELECT * FROM " + TABLE_BOOKMARKS + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        List<Bookmark> results = newArrayList();

        if (cursor.moveToFirst()) {
            do {
                Bookmark bookmark = new Bookmark();
                bookmark.setId(cursor.getLong(0));
                bookmark.setUrl(cursor.getString(1));
                bookmark.setTitle(cursor.getString(2));
                results.add(bookmark);
            } while (cursor.moveToNext());
        }

        return results;

    }


    public void insertBookmarks(List<Bookmark> bookmarks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(Bookmark bookmark : bookmarks){
            values.put(BOOKMARKS_URL, bookmark.getUrl());
            values.put(BOOKMARKS_TITLE, bookmark.getTitle());
        }

        db.insert(TABLE_BOOKMARKS, null, values);
    }
}