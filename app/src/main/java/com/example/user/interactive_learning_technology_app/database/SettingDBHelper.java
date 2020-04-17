package com.example.user.interactive_learning_technology_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SettingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "musicDBt.db";
    private static final String TableName = "TableName";
    private static final int DATABASE_VERSION = 1;

    public SettingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "_MusicName VARCHAR(50), " +
                "_MusicPath VARCHAR(50)," +
                "_musicUri VARCHAR(50)," +
                "_musicLong LONG" +
                ");";
        db.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL = "DROP TABLE " + TableName;
        db.execSQL(SQL);
    }
}