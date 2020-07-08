package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBHelper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.TABLE_NAME;

public class ExportDatabaseExcelTask extends AsyncTask<String, Void, Boolean> {

    public SQLiteDatabase mDatabase;
    private Context context;
    private ProgressDialog progressDialog; //這個可以快速顯示一個Progress


    public ExportDatabaseExcelTask(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("正在匯出Excel檔");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if(progressDialog.isShowing())
            progressDialog.dismiss();

        if(!aBoolean)
            Toast.makeText(context, "匯出失敗", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected Boolean doInBackground(String... params) {

        //創立Excel的檔名

        String fileName = "Test" + ".xls";

        //選擇要匯出的Table

        SearchDBHelper dbHelper = new SearchDBHelper(this.context);
        mDatabase = dbHelper.getWritableDatabase(); //寫入
        mDatabase = dbHelper.getReadableDatabase(); //讀取
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //將會在內部空間內建立一個資料夾

        File exportDir = new File(Environment.getExternalStorageDirectory().getPath(), "TestExport");

        if (!exportDir.exists())
            exportDir.mkdirs();

        //在TestExport下創建一個檔案

        File file = new File(exportDir, fileName);

        //設定輸出相關格式

        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook;

        try {
            //創立一個Excel檔

            workbook = Workbook.createWorkbook(file, wbSettings);

            //建立分頁

            WritableSheet sheet = workbook.createSheet(COLUMN_Name, 0);

            /**
             * 如果要建立第二分頁就會像 workbook.createSheet(CurrentAccountData.getAccountName(), 1);
             */

            //先建立我們Excel的標題

            sheet.addCell(new Label(0, 0, "學號"));
            sheet.addCell(new Label(1, 0, "姓名"));

            //開始放入表的資料，這邊請都用getString()

            if (cursor.moveToFirst()) {
                do {
                    int i = cursor.getPosition() + 1;
                    sheet.addCell(new Label(0, i, cursor.getString(0)));
                    sheet.addCell(new Label(1, i, cursor.getString(1)));
                }
                while (cursor.moveToNext());
            }

            cursor.close();
            workbook.write();
            workbook.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("eee1",e+"");
            return false;
        }  catch (RowsExceededException e) {
            e.printStackTrace();
            Log.d("eee2",e+"");
            return false;
        } catch (WriteException e) {
            e.printStackTrace();
            Log.d("eee3",e+"");
            return false;
        }
    }

}
