package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.AddFeedbackFrameSetting;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Main.MainFragment;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.Random;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackCount;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_Item;


public class ResultFragment extends Fragment {


    //==========[static]==========
    //arvg
    private String _name;
    private String _data;
    private String _rawFile;
    private Integer selectId=0;
    private SQLiteDatabase mDatabase;

    //sql string
    private String mItem;
    private String mAttentionHigh;
    private String mAttentionLow;
    private String mAttentionFeedBackWay;
    private String mFeedBackWaySecond;
    private String mFeedBackWayStopTipSecond;
    private String mFeedBackCount;

    //view
    private TextView SelectItem;
    private TextView Attention;
    private TextView attentionHigh;
    private TextView attentionLow;
    private TextView feedBackWay;
    private TextView fbwSec;
    private TextView fbwSecTips;
    private TextView detectMin;
    private TextView detectSec;
    private TextView detectRound;
    private TextView detectName;

    private Button saveButton;
    private Button unsaveButton;
    public  FragmentManager fragmentManager;
    private String loginId;



    public static Fragment newInstance(String data, String name, String _rawFile) {
        ResultFragment f = new ResultFragment();
        f._name = name;
        f._data = data;
        f._rawFile = _rawFile;

        return f;
    }

    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView detectName = view.findViewById(R.id.detectName);
        TextView SelectItem = view.findViewById(R.id.SelectItem) ;
        TextView Attention= view.findViewById(R.id.Attention) ;
        TextView attentionHigh= view.findViewById(R.id.attentionHigh) ;
        TextView attentionLow= view.findViewById(R.id.attentionLow) ;
        TextView feedBackWay= view.findViewById(R.id.feedBackWay) ;
        TextView fbwSec= view.findViewById(R.id.fbwSec) ;
        TextView fbwSecTips= view.findViewById(R.id.fbwSecTips) ;
        TextView detectMin= view.findViewById(R.id.detectMin) ;
        TextView detectSec= view.findViewById(R.id.detectSec) ;
        TextView detectRound= view.findViewById(R.id.detectRound) ;
        SettingDBHelper dbHelper = new SettingDBHelper(getActivity());
        saveButton =view.findViewById(R.id.save);
        unsaveButton = view.findViewById(R.id.unSave);
        mDatabase = dbHelper.getWritableDatabase();
        fragmentManager = getActivity().getSupportFragmentManager();
        final SaveResultFragment saveResultFragment = new SaveResultFragment();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, saveResultFragment);
                fragmentTransaction.commit();
            }
        });
        unsaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        String timeId = getActivity().getSharedPreferences("timeSelect", MODE_PRIVATE)
                .getString("USER", "");

        String userId = getActivity().getSharedPreferences("selectId", MODE_PRIVATE)
                .getString("USER", "");

        loginId = getActivity().getSharedPreferences("detectId", MODE_PRIVATE)
                .getString( "USER", "");
        String feedBackCount = getActivity().getSharedPreferences("feedbackCount", MODE_PRIVATE)
                .getString("USER", "");

        detectName.setText(loginId);
        selectId = Integer.valueOf(userId);

        LoadData();

        if (Integer.valueOf(mAttentionFeedBackWay)==0){
            feedBackWay.setText("視覺");
        }
        if (Integer.valueOf(mAttentionFeedBackWay)==1){
            feedBackWay.setText("震動");
        }
        if (Integer.valueOf(mAttentionFeedBackWay)==2){
            feedBackWay.setText("聲音");
        }
        SelectItem.setText(mItem);
        attentionHigh.setText(mAttentionHigh);
        attentionLow.setText(mAttentionLow);
        fbwSec.setText(mFeedBackWaySecond);
        fbwSecTips.setText(mFeedBackWayStopTipSecond);
        detectMin.setText(timeId);
        detectRound.setText(feedBackCount);
        return view;
    }

    public void LoadData() {

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM settingDataList", null);
        Log.d("787878",""+selectId);
        cursor.moveToPosition(selectId);
        mItem = cursor.getString(cursor.getColumnIndex(COLUMN_Item));
        mAttentionHigh = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionHigh));
        mAttentionLow = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionLow));
        mAttentionFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionFeedBackWay));
        mFeedBackWaySecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWaySecond));
        mFeedBackWayStopTipSecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWayStopTipSecond));

//        Log.d("??",mAttentionFeedBackWay);

        cursor.close();
    }
    public void LoadSearchData() {

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM searchDataList", null);

        cursor.moveToLast();
        String mId = cursor.getString(cursor.getColumnIndex(COLUMN_ID));

        Log.d("??",mId);
        mDatabase.delete("searchDataList", "id" + "=" + mId, null);

        cursor.close();
    }

    protected void showDialog(){

        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_unsave_check, null);
        dialog.setContentView(view);
        Button buttonY = view.findViewById(R.id.buttonY);
        Button buttonN = view.findViewById(R.id.buttonN);
        EditText editText = view.findViewById(R.id.checkId);


        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals(loginId)){
                    LoadSearchData();
                    Toast.makeText(getActivity(), "輸入正確，資料不儲存", Toast.LENGTH_SHORT).show();
                    final MainFragment mainFragment = new MainFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.center, mainFragment);
                    fragmentTransaction.commit();
                }else{
                    Toast.makeText(getActivity(), "輸入錯誤，請再試一次", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    private void del(){

//        String id = "1"; //刪除id為1的資料


    }
}
