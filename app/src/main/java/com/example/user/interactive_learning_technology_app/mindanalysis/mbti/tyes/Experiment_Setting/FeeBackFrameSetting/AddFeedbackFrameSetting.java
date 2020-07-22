package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBHelper;

import java.util.List;

import static android.app.Activity.RESULT_OK;
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


public class AddFeedbackFrameSetting extends Fragment {
    public SQLiteDatabase mDatabase;
    public List<String> list;
    public Spinner SpinnerItem;
    private Integer attRadioId=0;
    private Integer relRadioId=0;
    private Uri attUri;
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
        //選取mp3
        final String mimeType = "audio/*";
        final PackageManager packageManager = getActivity().getPackageManager();
        final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        final EditText EdtAttentionMp3 = view.findViewById(R.id.attentionMp3);
        final EditText EdtRelaxationMp3 = view.findViewById(R.id.relaxationMp3);

        //radioGroup
        RadioGroup radioGroupAtt = (RadioGroup) view.findViewById(R.id.radioGroupAtt);
        RadioGroup radioGroupRel = (RadioGroup) view.findViewById(R.id.radioGroupRel);
        radioGroupAtt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.attSight:
                        attRadioId = 0;
                        break;
                    case R.id.attShock:
                        attRadioId = 1;
                        break;
                    case R.id.attVoice:
                        attRadioId = 2;

                        intent.setType(mimeType);
                        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        if (list.size() > 0) {
                            // 如果有可用的Activity
                            Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
                            picker.putExtra(Intent.EXTRA_LOCAL_ONLY, false);
                            picker.setType(mimeType);
                            // 使用Intent Chooser
                            Intent destIntent = Intent.createChooser(picker, "選取MP3音樂");
                            startActivityForResult(destIntent,100);
                        } else {
                            // 沒有可用的Activity
                        }
                        break;
                }
                Log.d("pospos",""+attRadioId);
            }
        });
        radioGroupRel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.relSight:
                        relRadioId = 0;
                        break;
                    case R.id.relShock:
                        relRadioId = 1;
                        break;
                    case R.id.relVoice:
                        relRadioId = 2;

                        intent.setType(mimeType);
                        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        if (list.size() > 0) {
                            // 如果有可用的Activity
                            Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
                            picker.putExtra(Intent.EXTRA_LOCAL_ONLY, false);
                            picker.setType(mimeType);
                            // 使用Intent Chooser
                            Intent destIntent = Intent.createChooser(picker, "選取MP3音樂");
                            startActivityForResult(destIntent,100);
                        } else {
                            // 沒有可用的Activity
                        }
                        break;
                }
                Log.d("pospos",""+relRadioId);
            }
        });


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
                final String edtFbwSecText = EdtFbwSec.getText().toString();
                final String edtFbwSecTipsText = EdtFbwSecTips.getText().toString();


                String sql = "INSERT into '" + TABLE_NAME + "' ( '" + COLUMN_Name
                        + "','" + COLUMN_Item
                        + "','" + COLUMN_AttentionHigh
                        + "','" + COLUMN_AttentionLow
                        + "','" + COLUMN_AttentionFeedBackWay
                        + "','" + COLUMN_AttentionMp3Uri
                        + "','" + COLUMN_RelaxationHigh
                        + "','" + COLUMN_RelaxationLow
                        + "','" + COLUMN_RelaxationFeedBackWay
                        + "','" + COLUMN_RelaxationMp3Uri
                        + "','" + COLUMN_FeedBackWaySecond
                        + "','" + COLUMN_FeedBackWayStopTipSecond + "' ) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                Object[] mValue = new Object[]{edtNameText,spinnerItemText,edtAttentionHighText,edtAttentionLowText,attRadioId,attUri,
                                               edtRelaxationHighText,edtRelaxationLowText,relRadioId,"",edtFbwSecText,edtFbwSecTipsText};
                mDatabase.execSQL(sql,mValue);

                Cursor c = mDatabase.query(TABLE_NAME,                                         // 資料表名字
                        new String[]{COLUMN_ID,
                                COLUMN_Name,
                                COLUMN_Item,
                                COLUMN_AttentionHigh ,
                                COLUMN_AttentionLow,
                                COLUMN_AttentionFeedBackWay,
                                COLUMN_AttentionMp3Uri,
                                COLUMN_RelaxationHigh,
                                COLUMN_RelaxationLow,
                                COLUMN_RelaxationFeedBackWay,
                                COLUMN_RelaxationMp3Uri,
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
                    String mp3 = c.getString(c.getColumnIndex(COLUMN_AttentionMp3Uri));
                    String relWay = c.getString(c.getColumnIndex(COLUMN_RelaxationFeedBackWay));
                    String attWay = c.getString(c.getColumnIndex(COLUMN_AttentionFeedBackWay));
                    Log.v("7788",mp3+"//////"+mp3+"////"+mp3);

                }
                final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                final FeedbackFrameSettingsFragment fsaFragment = new FeedbackFrameSettingsFragment();

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (attRadioId.equals(2)){
            // 有選擇檔案
            Log.d("???","attatt");
            if ( resultCode == RESULT_OK ){
                // 取得檔案的 Uri
                attUri = data.getData();
                if( attUri != null ){

                    Log.d("??","///"+attUri);

                    try {
//                        撥放音樂
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.d("??","///"+attUri);
                    mediaPlayer.setDataSource(getActivity(), attUri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    }
                    catch (Exception e){
                        Log.d("///",e+"");
                    }
                }
                else{
                    Toast.makeText(getActivity(), "無效的檔案路徑 !!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "取消選擇檔案 !!", Toast.LENGTH_SHORT).show();
            }
        }

        if (relRadioId.equals(2)){
            // 有選擇檔案
            Log.d("???","relrel");
            if ( resultCode == RESULT_OK ){
                // 取得檔案的 Uri
                attUri = data.getData();
                if( attUri != null ){

                    Log.d("??","///"+attUri);

                    try {
                        //撥放音樂
//                    MediaPlayer mediaPlayer = new MediaPlayer();
//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    mediaPlayer.setDataSource(getActivity(), attUri);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
                    }
                    catch (Exception e){
                        Log.d("///",e+"");
                    }
                }
                else{
                    Toast.makeText(getActivity(), "無效的檔案路徑 !!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "取消選擇檔案 !!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
