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

import com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting.FeebackData;
import com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting.SettingAdapter;
import com.example.user.interactive_learning_technology_app.Experiment_Setting.Login.LoginActivity;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBHelper;

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

public class MainActivity extends AppCompatActivity {
    public SQLiteDatabase mDatabase;
    public ArrayList<FeebackData> mFeedbackData = new ArrayList<FeebackData>();
    public Cursor data;
    public SettingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mBottomView = (View) findViewById(R.id.bottom_view_main);
        final Button mExperimentSearchButton = (Button) mBottomView.findViewById(R.id.ExperimentSearchButton);
        final Button mExperimentSettingButton = (Button) mBottomView.findViewById(R.id.ExperimentSettingButton);
        final Button mBackHomeButton = (Button) mBottomView.findViewById(R.id.BackHomeButton);

//        addItem();

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
//        data = mDatabase.rawQuery("SELECT * FROM settingDataList",null);
//        data.moveToFirst();
////        mFeedbackData.clear();
//        for (int i=0 ; i==5;i++){
//            mFeedbackData.add(
//                    new FeebackData(
//                            data.getString(0),data.getString(1),data.getString(2),
//                            data.getString(3),data.getString(4),data.getString(5),
//                            data.getString(6),data.getString(7),data.getString(8),
//                            data.getString(9),data.getString(10))
//            );
//            Log.d("幾次了","////"+i+"////"+mFeedbackData.get(i).getId());
//            data.moveToNext();
//            adapter.notifyDataSetChanged();
//        }



    }
}
