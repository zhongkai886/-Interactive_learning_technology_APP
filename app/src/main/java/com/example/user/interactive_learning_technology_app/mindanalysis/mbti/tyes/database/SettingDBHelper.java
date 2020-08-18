package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.TABLE_NAME;

public class SettingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "123.db";
    private static final int DATABASE_VERSION = 1;

    public SettingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL = "DROP TABLE " + TABLE_NAME;
        db.execSQL(SQL);
    }
}