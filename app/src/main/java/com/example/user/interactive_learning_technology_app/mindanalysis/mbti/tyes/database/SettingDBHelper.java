package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationMp3Uri;
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