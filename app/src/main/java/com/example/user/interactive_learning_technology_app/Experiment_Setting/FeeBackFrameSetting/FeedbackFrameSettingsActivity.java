package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting.AddFeedbackFrameSetting;
import com.example.user.interactive_learning_technology_app.Main.MainFragment;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.database.SettingDBContract;
import com.example.user.interactive_learning_technology_app.database.SettingDBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedbackFrameSettingsActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedbackFrameSettingsActivity extends Fragment {
    public SQLiteDatabase mDatabase;
    public FeedbackFrameSettingsActivity() {
        // Required empty public constructor
    }

    public static FeedbackFrameSettingsActivity newInstance() {
        FeedbackFrameSettingsActivity fragment = new FeedbackFrameSettingsActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback_frame_settings_activity, container, false);
//        SettingDBHelper dbHelper = new SettingDBHelper(this.getContext());
//        mDatabase = dbHelper.getWritableDatabase();
//        addItem();

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final AddFeedbackFrameSetting addFragment = new  AddFeedbackFrameSetting();
        Button mFeedBackAdd = (Button) view.findViewById(R.id.FeedBackAdd);
        mFeedBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, addFragment);
                fragmentTransaction.commit();

            }
        });


        return view;
    }
//    private void addItem(){
//        SettingDBHelper dbHelper = new SettingDBHelper(this.getContext());
//        mDatabase = dbHelper.getWritableDatabase();
//
//
//        ContentValues cv = new ContentValues();
//        cv.put("_MusicName","12313123");
////        cv.put(SettingDBContract.SettingDataEntry.COLUMN_ID,"1");
////        cv.put("_name","a");
////        cv.put(SettingDBContract.SettingDataEntry.COLUMN_Item,"relaxation");
////        cv.put(SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond,"3");
////        cv.put(SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond,"2");
//        mDatabase.insert("TableName",null,cv);
//        Log.d("yo",""+cv);
//
//
//    }
}
