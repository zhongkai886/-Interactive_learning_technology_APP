package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class FeedbackFrameSettingsActivity extends Fragment {
    public SQLiteDatabase mDatabase;
    private SettingAdapter mAdapter;
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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SettingAdapter(getActivity(),getAllItems());
        recyclerView.setAdapter(mAdapter);
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

    private Cursor getAllItems(){
        return mDatabase.query(TABLE_NAME,                                         // 資料表名字
                new String[]{COLUMN_ID,
                        COLUMN_Name,
                        COLUMN_Item,
                        COLUMN_AttentionHigh ,
                        COLUMN_AttentionLow,
                        COLUMN_AttentionFeedBackWay,
                        COLUMN_RelaxationHigh,
                        COLUMN_RelaxationLow,
                        COLUMN_RelaxationFeedBackWay,
                        COLUMN_FeedBackWaySecond,
                        COLUMN_FeedBackWayStopTipSecond},  // 要取出的欄位資料
                null,                                              // 查詢條件式
                null,                                              // 查詢條件值字串陣列
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                COLUMN_ID,                                            // Order By字串語法(排序)
                null);
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
