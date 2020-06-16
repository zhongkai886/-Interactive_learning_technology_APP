package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.DataSearch.SearchFragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBHelper;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.ArrayList;

import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageAttention;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageRelaxation;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_DetectTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackCount;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackPassSeconds;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackSecondsGap;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_PointInTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.TABLE_NAME;

public class DataSearchFragment extends Fragment {
    public SQLiteDatabase mDatabase;
    public ArrayList<String> mCheckBoxDataList = new ArrayList<String>();
    public ArrayList<FeedbackData> feedbackDataList = new ArrayList<FeedbackData>();
    public RecyclerView recyclerView;
    public DataSearchFragment() {
    }

    public static DataSearchFragment newInstance(String param1, String param2) {
        DataSearchFragment fragment = new DataSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_search2, container, false);

        SearchDBHelper dbHelper = new SearchDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        InsertTable();
        LoadData();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }
    public void LoadData(){
        Cursor c = mDatabase.query(TABLE_NAME,                                         // 資料表名字
                new String[]{COLUMN_ID,COLUMN_Name,COLUMN_DetectTime,COLUMN_Item,
                        COLUMN_FeedBackCount,
                        COLUMN_AttentionHigh,COLUMN_AttentionLow,
                        COLUMN_RelaxationHigh,COLUMN_RelaxationLow,
                        COLUMN_AttentionMax,COLUMN_AttentionMin,
                        COLUMN_RelaxationMax,COLUMN_RelaxationMin,
                        COLUMN_FeedBackSecondsGap,COLUMN_FeedBackPassSeconds,
                        COLUMN_AverageAttention,COLUMN_AverageRelaxation,
                        COLUMN_PointInTime},  // 要取出的欄位資料
                null,                                              // 查詢條件式
                null,                                              // 查詢條件值字串陣列
                null,                                              // Group By字串語法
                null,                                              // Having字串法
                COLUMN_ID,                                            // Order By字串語法(排序)
                null);                                             // Limit字串語法

        while(c.moveToNext())
        {
            String id = c.getString(c.getColumnIndex(COLUMN_ID));    // 取出名字欄位資料
            String count = c.getString(c.getColumnIndex(COLUMN_FeedBackCount));
            Log.v("7788",id+"////////"+count);

        }
    }
    public void InsertTable(){
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(50), " +
                COLUMN_DetectTime + " VARCHAR(50)," +
                COLUMN_Item + " VARCHAR(50)," +
                COLUMN_FeedBackCount + " VARCHAR(50)," +
                COLUMN_AttentionHigh + " VARCHAR(50)," +
                COLUMN_AttentionLow + " VARCHAR(50)," +
                COLUMN_RelaxationHigh + " VARCHAR(50)," +
                COLUMN_RelaxationLow + " VARCHAR(50)," +
                COLUMN_AttentionMax + " VARCHAR(50)," +
                COLUMN_AttentionMin + " VARCHAR(50)," +
                COLUMN_RelaxationMax + " VARCHAR(50)," +
                COLUMN_RelaxationMin + " VARCHAR(50)," +
                COLUMN_FeedBackSecondsGap + " VARCHAR(50)," +
                COLUMN_FeedBackPassSeconds + " VARCHAR(50)," +
                COLUMN_AverageAttention + " VARCHAR(50)," +
                COLUMN_AverageRelaxation + " VARCHAR(50)," +
                COLUMN_PointInTime + " VARCHAR(50)" +
                ");";
        mDatabase.execSQL(SQL);

        String sql = "INSERT into '" + TABLE_NAME + "' ( '" + COLUMN_Name
                + "','" + COLUMN_DetectTime
                + "','" + COLUMN_Item
                + "','" + COLUMN_FeedBackCount
                + "','" + COLUMN_AttentionHigh
                + "','" + COLUMN_AttentionLow
                + "','" + COLUMN_RelaxationHigh
                + "','" + COLUMN_RelaxationLow
                + "','" + COLUMN_AttentionMax
                + "','" + COLUMN_AttentionMin
                + "','" + COLUMN_RelaxationMax
                + "','" + COLUMN_RelaxationMin
                + "','" + COLUMN_FeedBackSecondsGap
                + "','" + COLUMN_FeedBackPassSeconds
                + "','" + COLUMN_AverageAttention
                + "','" + COLUMN_AverageRelaxation
                + "','" + COLUMN_PointInTime + "' ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        for (int i = 0 ; i<5 ; i++){
            Object[] mValue = new Object[]{"安安","2001","Attention",
                    "5",
                    "70","20",
                    "80","30",
                    "100","5",
                    "98","3",
                    "2","5",
                    "45","30",
                    "wnfwefnwefnewfnwe"};
            mDatabase.execSQL(sql,mValue);
        }
    }
}

