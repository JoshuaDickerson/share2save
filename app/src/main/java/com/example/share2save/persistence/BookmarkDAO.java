package com.example.share2save.persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.share2save.model.Bookmark;

/**
 * Created by josh on 3/9/14.
 */
public class BookmarkDAO extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sharetosave";

    // Contacts table name
    private static final String TABLE_BOOKMARKS = "bookmarks";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";
    private static final String KEY_TITLE = "title";

    public BookmarkDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BookmarkDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_BOOKMARKS + "(" +KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_URL + " TEXT," + KEY_TITLE + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(db);
    }

    public Bookmark getBookmarkByTag(String tag){
        return new Bookmark();
    }
}
