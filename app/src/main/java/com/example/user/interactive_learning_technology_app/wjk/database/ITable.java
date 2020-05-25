package com.example.user.interactive_learning_technology_app.wjk.database;

public interface ITable {

    String TYPE_KEY_AUTO_NOTNULL = " PRIMARY KEY AUTOINCREMENT NOT NULL ";
    String TYPE_NULL = " NULL ";
    String TYPE_INTEGER = " INTEGER ";
    String TYPE_REAL = " REAL ";
    String TYPE_TEXT = " TEXT ";
    String TYPE_BLOB = " BLOB ";

    String name();

    String columnId();

    String[] columns();

}
