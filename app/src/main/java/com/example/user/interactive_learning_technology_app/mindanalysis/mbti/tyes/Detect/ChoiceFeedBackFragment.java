package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.TABLE_NAME;

public class ChoiceFeedBackFragment extends Fragment {
    private Button button;
    private Spinner spinnerFeedBack;
    private Spinner spinnerTime;

    private SQLiteDatabase  mDatabase;
    private ArrayList<String> ListId = new ArrayList<String>();
    private ArrayList<String> ListName = new ArrayList<String>();
    private ArrayList<String> ListItem = new ArrayList<String>();
    private ArrayList<String> ListWay = new ArrayList<String>();
    public ChoiceFeedBackFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
        mDatabase = dbHelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_choice_feed_back, container, false);
        CreateSetting();
        LoadData();
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final DetectLoginFragment detectLoginFragment = new DetectLoginFragment();
        final String[] timeSpinner= {"5","10","15","20","25","30","35","40","45","50","55","60"};
        final String[] timeChange= {"300","600","900","1200","1500","1800","2100","2400","2700","3000","3300","3600"};


        button = view.findViewById(R.id.enterButton);
        spinnerFeedBack = view.findViewById(R.id.FeedBackSpinner);
        spinnerTime = view.findViewById(R.id.TimeSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_item,timeSpinner);
        spinnerTime.setAdapter(arrayAdapter);
        spinnerFeedBack.setAdapter(new SpinnerAdapter(view.getContext(),ListId,ListName,ListItem));
        spinnerFeedBack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer  settingId = adapterView.getPositionForView(view);
                String selectId = ListWay.get(settingId);
                String selectPosition = ListId.get(settingId);
                Log.d("111113333",""+selectPosition);
                String selectAttentionWay = ListWay.get(settingId);

                SharedPreferences pref = getActivity().getSharedPreferences("selectId", MODE_PRIVATE);
                pref.edit()
                        .putString("USER", String.valueOf(settingId))
                        .commit();

                SharedPreferences pref3 = getActivity().getSharedPreferences("selectIdd", MODE_PRIVATE);
                pref3.edit()
                        .putString("USER", String.valueOf(settingId))
                        .commit();

                SharedPreferences pref2 = getActivity().getSharedPreferences("selectAttentionWay", MODE_PRIVATE);
                pref2.edit()
                        .putString("USER", selectAttentionWay)
                        .commit();
                Log.d("這是啥",""+selectAttentionWay);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer time = parent.getPositionForView(view);
                String timerSelect = timeChange[time];
                SharedPreferences pref = getActivity().getSharedPreferences("timeSelect", MODE_PRIVATE);
                pref.edit()
                        .putString("USER", timerSelect)
                        .commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, detectLoginFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    //將資料庫資料Load進去SpinnerAdapter
    public void LoadData() {

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM settingDataList", null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_Name));
            String item = cursor.getString(cursor.getColumnIndex(COLUMN_Item));
            String fb_way = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionFeedBackWay));
            ListId.add(id);
            ListName.add(name);
            ListItem.add(item);
            ListWay.add(fb_way);
            Log.d("comeIn", "LoadData: "+fb_way);

        }
        cursor.close();
    }
    public void  CreateSetting(){
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(50), " +
                COLUMN_Item + " VARCHAR(50)," +
                COLUMN_AttentionHigh + " VARCHAR(50)," +
                COLUMN_AttentionLow + " VARCHAR(50)," +
                COLUMN_AttentionFeedBackWay + " VARCHAR(50)," +
                COLUMN_AttentionMp3Uri + " VARCHAR(250)," +
                COLUMN_RelaxationHigh + " VARCHAR(50)," +
                COLUMN_RelaxationLow + " VARCHAR(50)," +
                COLUMN_RelaxationFeedBackWay + " VARCHAR(50)," +
                COLUMN_RelaxationMp3Uri + " VARCHAR(250)," +
                COLUMN_FeedBackWaySecond + " VARCHAR(50)," +
                COLUMN_FeedBackWayStopTipSecond + " VARCHAR(50)" +
                ");";
        mDatabase.execSQL(SQL);
    }
}
