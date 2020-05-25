package com.example.user.interactive_learning_technology_app.wjk.database;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class ITableValues {

    public ITableValues() {
    }

    public ITableValues(Cursor cursor) {
        read(cursor);
    }

    abstract public boolean verify();

    abstract public boolean read(Cursor cursor);

    abstract protected ContentValues ContentValues();

}
