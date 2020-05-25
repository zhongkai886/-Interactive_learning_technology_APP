package com.example.user.interactive_learning_technology_app.wjk.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class ISQLiteCenter extends SQLiteOpenHelper {

    protected ITable mTable;
    protected String table_name;
    protected String table_columnId;

//====================] SQLiteOpenHelper [====================

    public ISQLiteCenter(Context context, String name, CursorFactory factory, int version, ITable table) {
        super(context, name, factory, version);
        this.mTable = table;
        this.table_name = table.name();
        this.table_columnId = table.columnId();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        SQL_create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SQL_drop(db);
        SQL_create(db);
    }

//====================] Custom [====================

    public long insert(ITableValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long row = db.insert(table_name, null, values.ContentValues());
        db.close();

        return row;
    }

    public int delete(int columnId) {
        SQLiteDatabase db = getWritableDatabase();
        int row = db.delete(table_name, table_columnId + "=" + columnId, null);
        db.close();

        return row;
    }

    public int update(ITableValues values, int columnId) {
        SQLiteDatabase db = getWritableDatabase();
        int row = db.update(table_name, values.ContentValues(), table_columnId + "=" + columnId, null);
        db.close();

        return row;
    }

//====================] SQL [====================

    public void SQL_create(SQLiteDatabase db) {
        String[] columns = mTable.columns();

        String sql = "CREATE TABLE " + table_name + "(";
        for (int i = 0; i < columns.length; i += 2) {
            sql += columns[i] + columns[i + 1];
            if (i + 2 != columns.length) sql += ",";
        }
        sql += ")";

        db.execSQL(sql);
    }

    public void SQL_drop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + table_name);
    }

    /**
     * 取得table表所有資料<br/><br/>
     * select * from table_name
     */
    public Cursor SQL_select(SQLiteDatabase db) {
        return db.rawQuery(
                "select * from " + table_name,
                null);
    }


    /**
     * 指定column以降序排列<br/><br/>
     * select * from table_name<br/>
     * order by column_name desc
     */
    public Cursor SQL_select_desc(SQLiteDatabase db, String column_name) {
        return db.rawQuery(
                "select * from " + table_name +
                        " ORDER BY " + column_name + " DESC",
                null);
    }

    /**
     * 指定column以升序排列<br/><br/>
     * select * from table_name<br/>
     * order by column_name asc
     */
    public Cursor SQL_select_asc(SQLiteDatabase db, String column_name) {
        return db.rawQuery(
                "select * from " + table_name +
                        " ORDER BY " + column_name + " ASC",
                null);
    }

    /**
     * 讀取系統表格，取得所有Table名稱<br/>
     *
     * @return
     */
    public static Cursor SQL_select_table(SQLiteDatabase db) {
        return db.rawQuery(
                "select name from sqlite_master " +
                        "WHERE NOT name='sqlite_sequence' and NOT name='android_metadata'",
                null);
    }

//====================] Debug [====================

    protected void _ExportFile(String packagePath) {
        String databaseName = getDatabaseName();
        String path = String.format("data/%s/%s",
                packagePath.substring(0, packagePath.length() - 2),
                databaseName);

        File inputFile = new File(Environment.getDataDirectory(), path);
        File outputFile = new File(Environment.getExternalStorageDirectory(), databaseName);
        try {
            FileInputStream in = new FileInputStream(inputFile);
            FileOutputStream out = new FileOutputStream(outputFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Log.e("Database", "copied.");
        } catch (IOException e) {
            Log.e("Database", "fail.");
            Log.e("Database", "File1.exists:" + inputFile.exists());
            Log.e("Database", "File2.exists:" + outputFile.exists());
        }
    }

}
