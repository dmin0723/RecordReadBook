package com.dmin.android.recordreadbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dmin on 2016/4/21.
 */
public class DB extends SQLiteOpenHelper {

    public static final String CREATE_BOOK ="CREATE TABLE book("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "title TEXT DEFAULT NONE,"+
            "page TEXT DEFAULT NONE,"+
            "cpage Text DEFAULT '0',"+
            "percent TEXT DEFAULT '0')";

    private Context mContext;

    public DB(Context context){
        super(context,"book",null,1);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
