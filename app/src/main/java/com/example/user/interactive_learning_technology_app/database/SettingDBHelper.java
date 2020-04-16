package com.example.user.interactive_learning_technology_app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.user.interactive_learning_technology_app.database.SettingDBContract.*;

import androidx.annotation.Nullable;

public class SettingDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "settingDBList.db";
    public static final int DATABASE_VERSION = 1;


    public SettingDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_SETTINGDBLIST_TABLE = "CREATE TABLE"+
        SettingDataEntry.TABLE_NAME+"("+
                SettingDataEntry.COLUMN_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
                SettingDataEntry.TABLE_NAME+"TEXT NOT NULL,"+
                SettingDataEntry.COLUMN_FeedBackWayName
                SettingDataEntry.COLUMN_DetectItem
                SettingDataEntry.COLUMN_AttentionHigh
                SettingDataEntry.COLUMN_AttentionLow
                SettingDataEntry.COLUMN_DetectAttentionFeedBackWay
                SettingDataEntry.COLUMN_RelaxationHigh
                SettingDataEntry.COLUMN_RelaxationLow
                SettingDataEntry.COLUMN_DetectRelaxationFeedBackWay
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
