package com.example.user.interactive_learning_technology_app.wjk.database;

import android.content.ContentValues;
import android.database.Cursor;

public class UserTable extends ITableValues implements ITable {

//====================] Table [====================

    public static final String TABLE_NAME = "User";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DATA = "Data";
    public static final String COLUMN_RAWDATA = "RawData";

    @Override
    public String name() {
        return TABLE_NAME;
    }

    @Override
    public String columnId() {
        return COLUMN_ID;
    }

    @Override
    public String[] columns() {
        return new String[]{
                COLUMN_ID, TYPE_INTEGER + TYPE_KEY_AUTO_NOTNULL,
                COLUMN_DATE, TYPE_INTEGER,
                COLUMN_NAME, TYPE_TEXT,
                COLUMN_DATA, TYPE_TEXT,
                COLUMN_RAWDATA, TYPE_TEXT};
    }

//====================] ColumnValue [====================

    public int id;
    public long date;
    public String name;
    public String data;
    public String rawData;

    @Override
    public boolean verify() {
        if (date == 0) return false;
        if (name == null) return false;
        if (data == null) return false;
        if (rawData == null) return false;
        return true;
    }

    @Override
    public boolean read(Cursor cursor) {
        try {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            date = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            data = cursor.getString(cursor.getColumnIndex(COLUMN_DATA));
            rawData = cursor.getString(cursor.getColumnIndex(COLUMN_RAWDATA));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected ContentValues ContentValues() {
        ContentValues cv = new ContentValues();
        //id不計
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RAWDATA, rawData);
        return cv;
    }

}
