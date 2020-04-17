package com.example.user.interactive_learning_technology_app.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.Experiment_Setting.Login.LoginActivity;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBHelper;

public class MainActivity extends AppCompatActivity {
    public SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mBottomView = (View) findViewById(R.id.bottom_view_main);
        final Button mExperimentSearchButton = (Button) mBottomView.findViewById(R.id.ExperimentSearchButton);
        final Button mExperimentSettingButton = (Button) mBottomView.findViewById(R.id.ExperimentSettingButton);
        final Button mBackHomeButton = (Button) mBottomView.findViewById(R.id.BackHomeButton);
        addItem();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.center,fragment);
        fragmentTransaction.commit();


        mExperimentSettingButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginActivity loginActivity =new LoginActivity();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, loginActivity);
                fragmentTransaction.commit();
                mExperimentSettingButton.setVisibility(View.INVISIBLE); //隱藏
                mExperimentSearchButton.setVisibility(View.INVISIBLE);
                mBackHomeButton.setVisibility(View.VISIBLE);
            }
        });
        mBackHomeButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment mainFragment =new MainFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, mainFragment);
                fragmentTransaction.commit();
                mExperimentSettingButton.setVisibility(View.VISIBLE); //隱藏
                mExperimentSearchButton.setVisibility(View.VISIBLE);
                mBackHomeButton.setVisibility(View.INVISIBLE);

            }
        });
    }
    private void addItem(){
        SettingDBHelper dbHelper = new SettingDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();


        ContentValues cv = new ContentValues();
        cv.put("_MusicName","12313123");

//        cv.put(SettingDBContract.SettingDataEntry.COLUMN_ID,"1");
//        cv.put("_name","a");
//        cv.put(SettingDBContract.SettingDataEntry.COLUMN_Item,"relaxation");
//        cv.put(SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond,"3");
//        cv.put(SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond,"2");
        mDatabase.insert("TableName",null,cv);
        Log.d("yo",""+cv);
        Cursor c = mDatabase.query("TableName",                                         // 資料表名字
                new String[]{"_id", "_MusicName", "_MusicPath", "_musicUri" , "_musicLong"},  // 要取出的欄位資料
                null,                                              // 查詢條件式
                null,                                              // 查詢條件值字串陣列
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                "_id",                                            // Order By字串語法(排序)
                null);                                             // Limit字串語法

        while(c.moveToNext())
        {
            String id = c.getString(c.getColumnIndex("_id"));    // 取出名字欄位資料
            Log.v("777",id);
            Toast.makeText(this,"yoyoyo"+id,Toast.LENGTH_SHORT).show();
        }

    }
}
