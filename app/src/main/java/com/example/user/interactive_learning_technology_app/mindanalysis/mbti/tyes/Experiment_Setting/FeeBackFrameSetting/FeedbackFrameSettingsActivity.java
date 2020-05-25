package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

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

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.ArrayList;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.TABLE_NAME;

public class FeedbackFrameSettingsActivity extends Fragment {
    public SQLiteDatabase mDatabase;
    public SettingAdapter mAdapter;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    public ArrayList<FeedbackData> feedbackDataList = new ArrayList<FeedbackData>();
    public RecyclerView recyclerView;


    public FeedbackFrameSettingsActivity() {

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
        SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        LoadData();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SettingAdapter(feedbackDataList, this);
        recyclerView.setAdapter(mAdapter);

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final AddFeedbackFrameSetting addFragment = new AddFeedbackFrameSetting();
        final FeedbackFrameSettingsActivity Fragment = new FeedbackFrameSettingsActivity();

        Button mFeedBackAdd = (Button) view.findViewById(R.id.FeedBackAdd);
        Button mFeedBackDel = (Button) view.findViewById(R.id.FeedBackDel);
        mFeedBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, addFragment);
                fragmentTransaction.commit();


            }
        });
        mFeedBackDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData();
                Log.d("click", "" + mAdapter.getId());
            }
        });


        return view;
    }

    private Cursor getAllItems() {
        return mDatabase.query(TABLE_NAME,                                         // 資料表名字
                new String[]{COLUMN_ID,
                        COLUMN_Name,
                        COLUMN_Item,
                        COLUMN_AttentionHigh,
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
    public void LoadData() {
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM settingDataList", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_Name));
            String item = cursor.getString(cursor.getColumnIndex(COLUMN_Item));
            String attentionHigh = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionHigh));
            String attentionLow = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionLow));
            String attentionFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionFeedBackWay));
            String relaxationHigh = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationHigh));
            String relaxationLow = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationLow));
            String relaxationFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationFeedBackWay));
            String feedBackWaySecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWaySecond));
            String feedBackWayStopTipSecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWayStopTipSecond));
            FeedbackData feedbackData = new FeedbackData(id, name, item, attentionHigh, attentionLow, attentionFeedBackWay,
                    relaxationHigh, relaxationLow, relaxationFeedBackWay, feedBackWaySecond, feedBackWayStopTipSecond);
            feedbackDataList.add(feedbackData);
            Log.d("刷新",""+feedbackData);
        }
        cursor.close();
    }

    private void DeleteData() {

        mCheckBoxDataList=mAdapter.getId();
        Log.d("list", "mcheckbox" + mCheckBoxDataList);
        for (int i=0 ;i<mCheckBoxDataList.size();i++){
//            mDatabase.execSQL(TABLE_NAME);
            mDatabase.delete(TABLE_NAME, COLUMN_ID + "=" +mCheckBoxDataList.get(i)  , null);
            Log.d("fed","mcheckbox"+mCheckBoxDataList.get(i));
        }
        LoadData();


    }
}
