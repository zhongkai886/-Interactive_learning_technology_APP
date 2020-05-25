package com.example.user.interactive_learning_technology_app.wjk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteCenter extends ISQLiteCenter {

    protected static final int SQL_VERSION = 3;
    protected static final String SQL_NAME = "MindanAlysis.db";

    public SQLiteCenter(Context context) {
        super(context, SQL_NAME, null, SQL_VERSION, new UserTable());
    }

    public SQLiteCenter(Context context, ITable table) {
        super(context, SQL_NAME, null, SQL_VERSION, table);
    }

    private SQLiteCenter(Context context, String name, CursorFactory factory,
                         int version, ITable table) {
        super(context, name, factory, version, table);
    }

}
