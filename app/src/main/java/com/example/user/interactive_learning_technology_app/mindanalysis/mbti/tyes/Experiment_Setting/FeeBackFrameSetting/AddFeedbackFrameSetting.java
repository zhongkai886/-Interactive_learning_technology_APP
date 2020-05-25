package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.List;

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


public class AddFeedbackFrameSetting extends Fragment {
    public SQLiteDatabase mDatabase;
    public List<String> list;
    public Spinner SpinnerItem;
    private String attWay;
    private String relWay;

    public AddFeedbackFrameSetting() {
    }
    public static AddFeedbackFrameSetting newInstance(String param1, String param2) {
        AddFeedbackFrameSetting fragment = new AddFeedbackFrameSetting();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_feedback_frame_setting, container, false);
        Button SubmitButton = (Button) view.findViewById(R.id.SubmitButton);
        final EditText EdtName = (EditText) view.findViewById(R.id.name);
        Spinner SpinnerItem = (Spinner) view.findViewById(R.id.itemSpinner);
        final EditText EdtAttentionHigh =(EditText) view.findViewById(R.id.attentionHigh);
        final EditText EdtAttentionLow =(EditText) view.findViewById(R.id.attentionLow);
        final EditText EdtRelaxationHigh =(EditText) view.findViewById(R.id.relaxationHigh);
        final EditText EdtRelaxationLow =(EditText) view.findViewById(R.id.relaxationLow);
        final EditText EdtFbwSec =(EditText) view.findViewById(R.id.fbwSec);
        final EditText EdtFbwSecTips =(EditText) view.findViewById(R.id.fbwSecTips);
        RadioGroup radioGroupAtt = (RadioGroup) view.findViewById(R.id.radioGroupAtt);
        RadioGroup radioGroupRel = (RadioGroup) view.findViewById(R.id.radioGroupRel);



        SubmitButton.setOnClickListener(new View.OnClickListener() {
            SettingDBContract settingDBContract;
            private SettingAdapter mAdapter;
            @Override
            public void onClick(View view) {
                SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
                mDatabase = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                final String edtNameText = EdtName.getText().toString();
                final String spinnerItemText = SpinnerItem.getSelectedItem().toString();;
                final String edtAttentionHighText = EdtAttentionHigh.getText().toString();
                final String edtAttentionLowText = EdtAttentionLow.getText().toString();
//                final String AttWay;
                final String edtRelaxationHighText = EdtRelaxationHigh.getText().toString();
                final String edtRelaxationLowText = EdtRelaxationLow.getText().toString();
                String RelWay;
                final String edtFbwSecText = EdtFbwSec.getText().toString();
                final String edtFbwSecTipsText = EdtFbwSecTips.getText().toString();
                switch (radioGroupAtt.getCheckedRadioButtonId()){
                    case R.id.attSight:
                        attWay="0";
                    case R.id.attShock:
                        attWay="1";
                    case R.id.attVoice:
                        attWay="2";
                }
//                Log.d("yoman",""+attWay);
                switch (radioGroupRel.getCheckedRadioButtonId()){
                    case R.id.relSight:
                        relWay="0";
                        Log.d("yoman","0");
                    case R.id.relShock:
                        relWay="1";
                        Log.d("yoman","1");
                    case R.id.relVoice:
                        relWay="2";
                        Log.d("yoman","2");
                }
//                Log.d("yoman",""+relWay);

                cv.put(COLUMN_Name,edtNameText);
                cv.put(COLUMN_Item,spinnerItemText);
                cv.put(COLUMN_AttentionHigh,edtAttentionHighText);
                cv.put(COLUMN_AttentionLow,edtAttentionLowText);
                cv.put(COLUMN_AttentionFeedBackWay,attWay);
                cv.put(COLUMN_RelaxationHigh,edtRelaxationHighText);
                cv.put(COLUMN_RelaxationLow,edtRelaxationLowText);
                cv.put(COLUMN_RelaxationFeedBackWay,relWay);
                cv.put(COLUMN_FeedBackWaySecond,edtFbwSecText);
                cv.put(COLUMN_FeedBackWayStopTipSecond,edtFbwSecTipsText);
                mDatabase.insert(TABLE_NAME,null,cv);
//                mAdapter.swapCursor(getAllItems());


                Cursor c = mDatabase.query(TABLE_NAME,                                         // 資料表名字
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
                        null);                                             // Limit字串語法

                while(c.moveToNext())
                {
                    String id = c.getString(c.getColumnIndex(COLUMN_ID));    // 取出名字欄位資料
                    String name = c.getString(c.getColumnIndex(COLUMN_Name));
                    String attenhigh = c.getString(c.getColumnIndex(COLUMN_AttentionHigh));
                    String attWay = c.getString(c.getColumnIndex(COLUMN_AttentionFeedBackWay));
                    Log.v("7788",id+"//////"+name+"////"+attWay);

                }
                final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FeedbackFrameSettingsActivity fsaFragment = new  FeedbackFrameSettingsActivity();

                fragmentTransaction.replace(R.id.center, fsaFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    private Cursor getAllItems(){
        return mDatabase.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_ID + " DESC"
        );
    }
}
