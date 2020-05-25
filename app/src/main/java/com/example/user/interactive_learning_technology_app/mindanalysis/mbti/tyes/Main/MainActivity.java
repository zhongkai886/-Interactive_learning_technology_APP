package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.alchemy.mindcontroller.MindController;
import com.alchemy.mindcontroller.MindControllerFactory;
import com.alchemy.mindcontroller.MindsetValue;
import com.alchemy.wjk.mind.view.MindsetActivity;
import com.example.user.interactive_learning_technology_app.BuildConfig;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect.DetectFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackFrameSettingsActivity;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.SettingAdapter;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.Login.LoginActivity;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Handler.Callback {
    public SQLiteDatabase mDatabase;
    public ArrayList<FeedbackData> mFeedbackData = new ArrayList<FeedbackData>();
    public Cursor data;
    public SettingAdapter adapter;
    private MindController mMindCenter;
    private MenuItem mMindsignMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View mBottomView = (View) findViewById(R.id.bottom_view_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button mExperimentSearchButton = (Button) mBottomView.findViewById(R.id.ExperimentSearchButton);
        final Button mExperimentSettingButton = (Button) mBottomView.findViewById(R.id.ExperimentSettingButton);
        final Button mBackHomeButton = (Button) mBottomView.findViewById(R.id.BackHomeButton);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.center,fragment);
        fragmentTransaction.commit();


        if (savedInstanceState == null) {
            mMindCenter = MindControllerFactory.obtain(getApplicationContext(), new Handler(this));
            mMindCenter.start();
            if (BuildConfig.DEBUG)mMindCenter.DEBUG(true);
        }

        mExperimentSettingButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                LoginActivity loginActivity =new LoginActivity();
//                DetectFragment detectFragment =new DetectFragment();
//                FeedbackFrameSettingsActivity frameSettingsActivity = new FeedbackFrameSettingsActivity();

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

    @Override
    public void onResume() {
        super.onResume();
        if(mMindCenter!=null)mMindCenter.pause(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mMindCenter!=null)mMindCenter.pause(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMindCenter!=null)mMindCenter.stop();
        if(BuildConfig.DEBUG)mMindCenter.DEBUG(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMindsignMenu = menu.findItem(R.id.action_bluetooth);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_bluetooth) {
            Intent intent = new Intent(MainActivity.this, MindsetActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if (mMindsignMenu == null) {
            mMindsignMenu.setIcon(R.drawable.icon03);
            return true;
        }

        int w = message.what;
        int v = message.arg1;

        switch (w) {
            case MindsetValue.MSG_STATE_CHANGE:
                switch (v) {
                    case MindsetValue.STATE_IDLE:
                        mMindsignMenu.setIcon(R.drawable.transparent);
                        break;
                    case MindsetValue.STATE_CONNECTED:
                        mMindsignMenu.setIcon(R.drawable.icon01);
                        break;
                    case MindsetValue.STATE_CONNECTING:
                        mMindsignMenu.setIcon(R.drawable.icon03);
                        break;
                    case MindsetValue.STATE_DISCONNECTED:
                        mMindsignMenu.setIcon(R.drawable.transparent);
                        break;
                }
                break;
            case MindsetValue.MSG_POOR_SIGNAL:
                if (v > 100) mMindsignMenu.setIcon(R.drawable.icon01);
                else if (v > 30) mMindsignMenu.setIcon(R.drawable.icon01);
                else if (v == 0) mMindsignMenu.setIcon(R.drawable.icon01);
                break;
        }
        return true;
    }
}
