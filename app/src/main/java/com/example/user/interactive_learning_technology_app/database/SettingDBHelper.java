package com.example.user.interactive_learning_technology_app.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting.FeedbackData;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.database.SettingDBContract.SettingDataEntry.TABLE_NAME;

public class SettingDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "123.db";
    private static final int DATABASE_VERSION = 1;
    public SQLiteDatabase mDatabase;

    public SettingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(50), " +
                COLUMN_Item + " VARCHAR(50)," +
                COLUMN_AttentionHigh + " VARCHAR(50)," +
                COLUMN_AttentionLow + " VARCHAR(50)," +
                COLUMN_AttentionFeedBackWay + " VARCHAR(50)," +
                COLUMN_RelaxationHigh + " VARCHAR(50)," +
                COLUMN_RelaxationLow + " VARCHAR(50)," +
                COLUMN_RelaxationFeedBackWay + " VARCHAR(50)," +
                COLUMN_FeedBackWaySecond + " VARCHAR(50)," +
                COLUMN_FeedBackWayStopTipSecond + " VARCHAR(50)" +
                ");";
        db.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL = "DROP TABLE " + TABLE_NAME;
        db.execSQL(SQL);
    }

//    public List<String> getDataList(){
//        List<String> List = new ArrayList<String>();
//        String selectQuery = "SELECT * FROM " +  TABLE_NAME;
//        Cursor c = mDatabase.rawQuery(selectQuery,null);
//        if (c.moveToFirst()) {
//            do {
//                List.add(c.getString(1));
//
//            } while (c.moveToNext());
//        }
//        return List;
//    }
//

}