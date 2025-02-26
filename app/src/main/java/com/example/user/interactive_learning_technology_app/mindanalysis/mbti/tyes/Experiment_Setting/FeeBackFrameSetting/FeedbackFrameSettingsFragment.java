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
import java.util.List;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_HoldSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.TABLE_NAME;

public class FeedbackFrameSettingsFragment extends Fragment {
    public SQLiteDatabase mDatabase;
    public SettingAdapter mAdapter;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    public ArrayList<Integer> mCheckBoxPositionList = new ArrayList<Integer>();
    public List<FeedbackData> feedbackDataList = new ArrayList<FeedbackData>();
    public RecyclerView recyclerView;


    public FeedbackFrameSettingsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedback_frame_settings, container, false);
//        SettingDBHelper dbHelper = new SettingDBHelper(this.getContext());
//        mDatabase = dbHelper.getWritableDatabase();
//        addItem();
        SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();
        CreateSetting();
        LoadData();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new SettingAdapter(feedbackDataList, this);
        recyclerView.setAdapter(mAdapter);

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final AddFeedbackFrameSetting addFragment = new AddFeedbackFrameSetting();
        final FeedbackFrameSettingsFragment Fragment = new FeedbackFrameSettingsFragment();

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
//                updateData(feedbackDataList);

            }
        });
        return view;
    }

    private Cursor getAllItems() {
        return mDatabase.query(TABLE_NAME,  // 資料表名字
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
            String attentionMp3Uri = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionMp3Uri));
            String relaxationHigh = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationHigh));
            String relaxationLow = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationLow));
            String relaxationFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationFeedBackWay));
            String relaxationMp3Uri = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationMp3Uri));
            String feedBackWaySecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWaySecond));
            String feedBackWayHoldSecond = cursor.getString(cursor.getColumnIndex(COLUMN_HoldSecond));
            String feedBackWayStopTipSecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWayStopTipSecond));
            FeedbackData feedbackData = new FeedbackData(id, name, item, attentionHigh, attentionLow, attentionFeedBackWay,
                    attentionMp3Uri,relaxationHigh, relaxationLow, relaxationFeedBackWay,relaxationMp3Uri,feedBackWaySecond, feedBackWayHoldSecond, feedBackWayStopTipSecond);
            feedbackDataList.add(feedbackData);
            Log.d("刷新",""+feedbackData);
        }
        cursor.close();
    }

    private void DeleteData() {

//        mCheckBoxDataList=mAdapter.getId();
//        mCheckBoxPositionList = mAdapter.getCheckBoxPositionList();
//        Log.d("list", "mcheckbox" + mCheckBoxDataList);
//        for (int i=0 ;i<mCheckBoxDataList.size();i++){
////            mDatabase.execSQL(TABLE_NAME);
//            mDatabase.delete(TABLE_NAME, COLUMN_ID + "=" +mCheckBoxDataList.get(i)  , null);
//            Log.d("fed","mcheckbox"+mCheckBoxDataList.get(i));
//            feedbackDataList.remove(feedbackDataList.get(mCheckBoxPositionList.get(i)));
////            mAdapter.notifyItemRemoved(i);
//            Log.d("xxxx",""+feedbackDataList.size());
//        }

        //從adapter中取回更新後資料
        feedbackDataList=mAdapter.getmFeedbackDataList();
        List<Integer> removeList=new ArrayList<>();
        //勾選項目List驗證check
        Log.d("qqqqqqqqqqqqq", "qqqqqqqqqqqqqqsize: "+feedbackDataList.size());
        for (int i=0;i<feedbackDataList.size();i++){
            Log.d("qqqqqqqqqqqqq", "i:"+i+"DeleteData: "+feedbackDataList.get(i).getCheck());
            if (feedbackDataList.get(i).getCheck()){
                mDatabase.delete(TABLE_NAME, COLUMN_ID + "=" +feedbackDataList.get(i).getId()  , null);
//                feedbackDataList.remove(feedbackDataList.get(i));
                //remove集合收集Check過的資料
                removeList.add(i);

            }
        }

        Log.d("eee",""+removeList);
        //刪除Check過的每筆資料
        for (int i=removeList.size()-1;i>=0;i--){
            feedbackDataList.remove(feedbackDataList.get(removeList.get(i)));

        }
        mAdapter.notifyDataSetChanged();
    }

    public void  CreateSetting(){
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(250), " +
                COLUMN_Item + " VARCHAR(250)," +
                COLUMN_AttentionHigh + " VARCHAR(250)," +
                COLUMN_AttentionLow + " VARCHAR(250)," +
                COLUMN_AttentionFeedBackWay + " VARCHAR(250)," +
                COLUMN_AttentionMp3Uri + " VARCHAR(250)," +
                COLUMN_RelaxationHigh + " VARCHAR(250)," +
                COLUMN_RelaxationLow + " VARCHAR(250)," +
                COLUMN_RelaxationFeedBackWay + " VARCHAR(250)," +
                COLUMN_RelaxationMp3Uri + " VARCHAR(250)," +
                COLUMN_FeedBackWaySecond + " VARCHAR(250)," +
                COLUMN_HoldSecond + " VARCHAR(250)," +
                COLUMN_FeedBackWayStopTipSecond + " VARCHAR(250)" +
                ");";
        mDatabase.execSQL(SQL);
    }
}
