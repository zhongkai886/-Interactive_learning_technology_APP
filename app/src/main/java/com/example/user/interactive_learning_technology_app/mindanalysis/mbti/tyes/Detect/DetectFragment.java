package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alchemy.mindcontroller.MindController;
import com.alchemy.mindcontroller.MindControllerFactory;
import com.alchemy.mindlibrary.tool.MindDetectTool;
import com.alchemy.mindlibrary.tool.MindDetectToolMulti;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting.FeedbackData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBHelper;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract;
import com.example.user.interactive_learning_technology_app.widget.PreferencesCenter;
import com.example.user.interactive_learning_technology_app.widget.StringMultiple;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import wjk.android.chart.adapter.NumberAdapter;

import static android.content.Context.MODE_PRIVATE;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AttentionMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageAttention;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_AverageRelaxation;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_DetectTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_DetectTimeCount;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackCount;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackPassSeconds;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackSecondOutput;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_FeedBackSecondsGap;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_ID;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Item;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Name;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Number;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_PointInTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_SecondsOutput;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.TABLE_NAME;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_AttentionMp3Uri;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWaySecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_FeedBackWayStopTipSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_HoldSecond;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationFeedBackWay;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.database.SettingDBContract.SettingDataEntry.COLUMN_RelaxationMp3Uri;


public class DetectFragment extends Fragment implements MindDetectToolMulti.Listen{
    private boolean mInitialCreate;
    public SQLiteDatabase sqLiteDatabase;
    private onCompleteListener mListener;
    //mind
    private MindController mMindCenter;
    private MindDetectToolMulti mTimeDetect;
    private int resultId;
    //view
    public Button mStartButton;
    public Button mPauseButton;
    public Button mStopButton;
    public Button mRecordPointButton;
    public EditText mUserNameEdit;
    public TextView mTimerText;
    public TextView mPointValue;
    public TextView mDetectId;
    public MindDetectTool mindDetectTool ;
    public AlertDialog.Builder builder;
    public AlertDialog dialog;

    public String[] mNumPoint= {"1","2","3","4","5","6","7","8","9","10"};
    public String[] mIdPoint= {"A","B","C","D","E"};
    public ArrayList<String> pointData = new ArrayList<String>();
    Handler handler = new Handler();
    boolean toast = true;
    public Integer mSecondStart = 0;
    public Integer mSecondGap = 0;
    public Integer mSecondNeed= 0;
    public Integer mSecondHold= 0;
    public Integer mSecondHoldNeed= 0;

    //member
    public NumberAdapter mChartAdapterAtt;
    public NumberAdapter mChartAdapterMed;
    private String mTimeDetect_data;
    public String mindData;
    public String nameData;
    public String rawData;
    private ArrayList<String> attention_ArrayList;
    public String sqlId="";

    public String mNum="";
    public String mId="";
    //checkpoint資料
    public String pointDataSql = "";
    //load檔案
    public ArrayList<FeedbackData> feedbackDataList = new ArrayList<FeedbackData>();
    public String settingId = "";

    //上傳測驗資料的變數
    public Integer detectTotalCount=0;
    public String detectId="";
    public String detectName="";
    public CharSequence dateTime="";
    public String detectItem="";
    public String detectSecond="";
    public String detectSecondGap="";
    public Integer mAttention=0;
    public String mAttentionHigh="";
    public String mAttentionLow="";
    public String mRelaxationHigh="";
    public String mRelaxationLow="";
    public Integer mAttentionMax=0;
    public Integer mAttentionMin=0;
    public Integer mRelaxationMax=0;
    public Integer mRelaxationMin=0;
    public double mAverageAttention;
    public double mAverageRelaxation;

    public String mDetectTimeCount="";

    public String mSecondsOutput="";

    public String mFeedBackSecondOutput="";

    public MediaPlayer mediaPlayer = new MediaPlayer();
    public String mp3Uri ="";

    public Integer fb_Way =0;

    public ArrayList<Integer> mAttentionList = new ArrayList<>();

    public ArrayList<Integer> mRelaxationList = new ArrayList<>();
    //設定檔取值

    public Integer endTime=0;

    public String timeId="";


//    public String[] myResArray = getResources().getStringArray(R.array.aE);
//    public List<String> idArray = Arrays.asList(myResArray);
//    List<String> myArrayList = Arrays.asList(getResources().getStringArray(R.array.aE));

//    public ArrayList<String> idArray = new ArrayList<String>(R.array.aE);

    @Override
    public void onTimerUp() {
//        CallApi();
//        _result();
        mTimeDetect.reset();
        _state("stop");
        mChartAdapterAtt.removeAll();
        mChartAdapterMed.removeAll();
    }

    @Override
    public void onFilter(boolean f) {


    }

    @Override
    public void onRound(int r) {
        mTimeDetect_data = StringMultiple.encode(mTimeDetect_data, mTimeDetect.getData());
    }

    @Override
    public void onMindPower() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            toast = true;

            mChartAdapterAtt.add(mTimeDetect.attention);
            if (mChartAdapterAtt.count() > 30) mChartAdapterAtt.remove(0);

            mChartAdapterMed.add(mTimeDetect.meditation);
            if (mChartAdapterMed.count() > 30) mChartAdapterMed.remove(0);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    mTimerText.setText(mTimeDetect.getCountdownRound() + "-" + mTimeDetect.getCountdownTime());
                    endTime = mTimeDetect.getCountdownTime();
                    mTimerText.setText(mTimeDetect.getCountdownRound() + "-" + endTime);
                }
            });
//            //網路是否已連線(true or false)
//            mNetworkInfo.isConnected();
//            //網路連線方式名稱(WIFI or mobile)
//            mNetworkInfo.getTypeName();
//            //網路連線狀態
//            mNetworkInfo.getState();
//            //網路是否可使用
//            mNetworkInfo.isAvailable();
//            //網路是否已連接or連線中
//            mNetworkInfo.isConnectedOrConnecting();
//            //網路是否故障有問題
//            mNetworkInfo.isFailover();
//            //網路是否在漫遊模式
//            mNetworkInfo.isRoaming();
        } else if (mNetworkInfo == null) {
            if (toast) {
                mTimeDetect.paustart();
                _state("pause");
                Toast.makeText(getContext(), "internet fail", Toast.LENGTH_SHORT).show();
                toast = false;
            }
        }

    }

    public interface onCompleteListener {
        void onComplete(DetectFragment f);
    }

    public DetectFragment() {
    }

    public static DetectFragment newInstance() {
        DetectFragment fragment = new DetectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChartAdapterAtt = new NumberAdapter();
        mChartAdapterMed = new NumberAdapter();
        attention_ArrayList = new ArrayList<String>();
        mChartAdapterAtt.color = getResources().getColor(R.color.chart_attention);
        mChartAdapterMed.color = getResources().getColor(R.color.chart_meditation);
        mindDetectTool = new MindDetectTool();
        mTimeDetect = new MindDetectToolMulti(30);
        mTimeDetect.setListen(this);
        mMindCenter = MindControllerFactory.obtain(getActivity().getApplicationContext(), mTimeDetect.getHandler());
        mMindCenter.start();

        PreferencesCenter pc = new PreferencesCenter(getActivity());
        mTimeDetect.setFiltEnable(pc.DetectMind_filter_enable);
        mTimeDetect.setFilter(pc.DetectMind_filter_alphabeta, pc.DetectMind_filter_theta);


        if (savedInstanceState != null) {
            mInitialCreate = false;
        } else {
            mInitialCreate = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detect, container, false);

        SearchDBHelper dbHelper = new SearchDBHelper(getActivity());
        sqLiteDatabase = dbHelper.getWritableDatabase();
        mTimerText = (TextView) view.findViewById(R.id.fragment_detect_title);
        mStartButton = (Button) view.findViewById(R.id.fragment_detect_start);
//        mPauseButton = (Button) view.findViewById(R.id.removeButton);
//        mStopButton = (Button) view.findViewById(R.id.stopButton);

        mPauseButton = (Button) view.findViewById(R.id.fragment_detect_pause);
        mStopButton = (Button) view.findViewById(R.id.fragment_detect_stop);
        mDetectId = view.findViewById(R.id.detectId);
        mRecordPointButton = (Button)view.findViewById(R.id.recordPoint);
        mUserNameEdit = (EditText) view.findViewById(R.id.fragment_detect_username);
        mPointValue = (TextView)view.findViewById(R.id.recordPointTx);
        builder = new AlertDialog.Builder(getActivity());


        _initView();

        return view;
    }

    public void _initView() {
        LoadData();
        //share選擇的時間
        timeId = getActivity().getSharedPreferences("timeSelect", MODE_PRIVATE)
                .getString("USER", "");
        Log.d("eeeee",""+timeId);
        //抽資料要用SqlId 選擇的設定檔id
        settingId = getActivity().getSharedPreferences("selectId", MODE_PRIVATE)
                .getString("USER", "");

        mp3Uri = feedbackDataList.get(Integer.valueOf(settingId)).getAttentionMp3Uri();

        String detectId = getActivity().getSharedPreferences("detectId",MODE_PRIVATE)
                .getString("USER","");

        mDetectId.setText(detectId);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = Integer.valueOf(timeId);
                mTimeDetect.setTimer(t);

                mTimeDetect.setRound(1);
                mTimeDetect_data = "";
                mTimeDetect.start();
                _state("start");
                fb_Way = Integer.valueOf(getActivity().getSharedPreferences("selectAttentionWay", MODE_PRIVATE)
                        .getString("USER", ""));
                //取得開始秒數
                mSecondStart =Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getWaySecond());
                mSecondGap =Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getWayStopTipSecond());
                mSecondNeed=Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getWayStopTipSecond());
                mSecondHold=Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getHoldSecond());
                mSecondHoldNeed=Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getHoldSecond());
                Log.d("secsecsec",""+mSecondHold);
                handler.postDelayed(getData,500);



            }
        });
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mTimeDetect.paustart();
                if (mTimeDetect.isRunning()) mTimeDetect.pause();
                else{
                    showRestartDialog();
//                    mTimeDetect.reset();
//                    int t = Integer.valueOf(timeId);
//                    mTimeDetect.setTimer(t);
//
//                    mTimeDetect.setRound(1);
//                    mTimeDetect_data = "";
//                    mTimeDetect.start();
//                    _state("start");
//                    fb_Way = Integer.valueOf(getActivity().getSharedPreferences("selectAttentionWay", MODE_PRIVATE)
//                            .getString("USER", ""));
//                    //取得開始秒數
//                    mSecondGaps=Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getWaySecond());
//
//                    handler.postDelayed(getData,500);
                }
                _state("pause");
            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeDetect.stop();
                mTimeDetect.reset();
                _state("stop");
            }
        });
        mRecordPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void _state(String state) {
        if (state.equals("start")) {
            mStartButton.setVisibility(View.GONE);
            mStopButton.setVisibility(View.VISIBLE);
            mPauseButton.setVisibility(View.VISIBLE);
        } else if (state.equals("pause")) {
            mPauseButton.setText(mTimeDetect.isRunning() ?
                    "暫停" : "繼續");
        } else if (state.equals("stop")) {
            mStartButton.setVisibility(View.VISIBLE);
            mStopButton.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.GONE);
            _result();
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            final ResultFragment resultFragment = new ResultFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.center, resultFragment);
            fragmentTransaction.commit();
        }
    }

    private void _result(){ //測驗資料的19筆欄位

        dataHandle();
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(250), " +
                COLUMN_Number + " VARCHAR(250), " +
                COLUMN_DetectTime + " VARCHAR(250)," +
                COLUMN_Item + " VARCHAR(250)," +
                COLUMN_FeedBackCount + " VARCHAR(250)," +
                COLUMN_AttentionHigh + " VARCHAR(250)," +
                COLUMN_AttentionLow + " VARCHAR(250)," +
                COLUMN_RelaxationHigh + " VARCHAR(250)," +
                COLUMN_RelaxationLow + " VARCHAR(250)," +
                COLUMN_AttentionMax + " VARCHAR(250)," +
                COLUMN_AttentionMin + " VARCHAR(250)," +
                COLUMN_RelaxationMax + " VARCHAR(250)," +
                COLUMN_RelaxationMin + " VARCHAR(250)," +
                COLUMN_FeedBackSecondsGap + " VARCHAR(250)," +
                COLUMN_FeedBackPassSeconds + " VARCHAR(250)," +
                COLUMN_AverageAttention + " VARCHAR(250)," +
                COLUMN_AverageRelaxation + " VARCHAR(250)," +

                COLUMN_DetectTimeCount + " VARCHAR(250)," +

                COLUMN_SecondsOutput + " VARCHAR(250)," +

                COLUMN_FeedBackSecondOutput + " VARCHAR(3000)," +

                COLUMN_PointInTime + " VARCHAR(250)" +
                ");";
        sqLiteDatabase.execSQL(SQL);


        String sql = "INSERT into '" + TABLE_NAME + "' ( '" +COLUMN_Name
                + "','" + COLUMN_Number
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
                + "','" + COLUMN_FeedBackSecondsGap //間隔
                + "','" + COLUMN_FeedBackPassSeconds // 開始忽略的秒數
                + "','" + COLUMN_AverageAttention
                + "','" + COLUMN_AverageRelaxation

                + "','" + COLUMN_DetectTimeCount
                + "','" + COLUMN_SecondsOutput
                + "','" + COLUMN_FeedBackSecondOutput

                + "','" + COLUMN_PointInTime + "' ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //缺少三格
        Object[] mValue = new Object[]{detectName,detectId,dateTime,detectItem,
                detectTotalCount,mAttentionHigh,mAttentionLow,mRelaxationHigh,
                mRelaxationLow,mAttentionMax,mAttentionMin,mRelaxationMax,
                mRelaxationMin,detectSecondGap, detectSecond,mAverageAttention,
                mAverageRelaxation,mDetectTimeCount,"暫時為空",mFeedBackSecondOutput,pointDataSql};

        //測試用資料

        Object[] mValueTest = new Object[]{"名稱","編號","實驗日期","回饋方式",
                "總回饋次數","A高","A低","R高",
                "r低","A最大","A最小","R最大",
                "R最大","間隔秒數","忽略秒數","平均A",
                "平均R","資料"};
//        Log.d("sss",""+mAttentionHighCount);



//        SQLiteCenter center = new SQLiteCenter(getActivity());
//        UserTable values = new UserTable();
//        values.data = mTimeDetect_data;
        sqLiteDatabase.execSQL(sql,mValue);
//        values.date = System.currentTimeMillis();
//        values.name = mUserNameEdit.getText().toString();
//        Log.d("yoyoyyo",""+values.data);
//        Log.d("yoyoyyo",""+values.name);
//        if (values.name.length() <= 0)
//            values.name = "匿名";
//        values.rawData = "";
//        if (values.name.equals("")) values.name = mUserNameEdit.getHint().toString();
//        center.insert(values);

        mindData = mTimeDetect_data;
//        nameData = values.name;
        rawData = "";
//        if (mListener != null) mListener.onComplete();
        SharedPreferences pref = getActivity().getSharedPreferences("feedbackCount", MODE_PRIVATE);
        pref.edit()
                .putString("USER", detectTotalCount.toString())
                .commit();

    }
    public void dataHandle(){
        Log.d("eee1",""+endTime);
        endTime = Integer.valueOf(timeId) - endTime -1;
        Log.d("eee2",""+endTime);
        SharedPreferences pref = getActivity().getSharedPreferences("endTime", MODE_PRIVATE);
        pref.edit()
                .putString("USER",String.valueOf(endTime))
                .commit();

        //登入編號
        detectId = getActivity().getSharedPreferences("detectId", MODE_PRIVATE)
                .getString("USER", "");
        //登入名稱eeee
        detectName = getActivity().getSharedPreferences("detectName",MODE_PRIVATE)
                .getString("USER","");

        //測驗日期
        Calendar mCal = Calendar.getInstance();
        dateTime = DateFormat.format("yyyy-MM-dd kk:mm:ss", mCal.getTime());    // kk:24小時制, hh:12小時制

        //item
        Log.d("aaaaa",""+feedbackDataList.get(Integer.valueOf(settingId)).getItem());
        detectItem = feedbackDataList.get(Integer.valueOf(settingId)).getAttentionWay();
        //回饋間隔秒數
        detectSecondGap = feedbackDataList.get(Integer.valueOf(settingId)).getWayStopTipSecond();
        Log.d("0101000",""+detectSecondGap);
        //忽略的秒數
        detectSecond = feedbackDataList.get(Integer.valueOf(settingId)).getWaySecond();


        mAttentionHigh = feedbackDataList.get(Integer.valueOf(settingId)).getAttentionHigh();

        mAttentionLow = feedbackDataList.get(Integer.valueOf(settingId)).getAttentionLow();

        mRelaxationHigh = feedbackDataList.get(Integer.valueOf(settingId)).getRelaxationHigh();

        mRelaxationLow = feedbackDataList.get(Integer.valueOf(settingId)).getRelaxationLow();

        mAttentionMax = Collections.max(mAttentionList);

        mAttentionMin = Collections.min(mAttentionList);

        mRelaxationMax =Collections.max(mRelaxationList);

        mRelaxationMin =Collections.min(mRelaxationList);

        mAverageAttention = calculateAverage(mAttentionList);

        mAverageRelaxation = calculateAverage(mRelaxationList);


        mDetectTimeCount = String.valueOf(endTime);

        Log.d("maxmaxatt",""+mAttentionList);
        Log.d("maxmaxrel",""+mRelaxationList);
    }
    private double calculateAverage(List<Integer> marks) {
        Integer sum = 0;
        if(!marks.isEmpty()) {
            for (Integer mark : marks) {
                sum += mark;
            }
            return sum.doubleValue() / marks.size();
        }
        return sum;
    }
    public void changeSightColor(Integer attentionValue){
        //處理視覺回饋時的資料區間的顏色
        if (fb_Way==0){
            if (attentionValue>0 && attentionValue<20){
            mPointValue.setBackground(mPointValue.getContext().getDrawable(R.drawable.shape_oval));

            }
            if(attentionValue>20 && attentionValue<40){
                mPointValue.setBackground(mPointValue.getContext().getDrawable(R.drawable.shape_oval1));
            }
            if(attentionValue>40 && attentionValue<60){
                mPointValue.setBackground(mPointValue.getContext().getDrawable(R.drawable.shape_oval2));
            }
            if(attentionValue>60 && attentionValue<80){
                mPointValue.setBackground(mPointValue.getContext().getDrawable(R.drawable.shape_oval3));
            }
            if(attentionValue>80 && attentionValue<100){
                mPointValue.setBackground(mPointValue.getContext().getDrawable(R.drawable.shape_oval4));
            }
        }
    }

    public void changeTextView(Integer attentionValue){
        mPointValue.setText(attentionValue.toString());
    }
    private Runnable getData = new Runnable() {
        public void run() {
            //設定初始秒數 ex: 5 ;不停減少之後低於0進入實驗開始收集資料
            mSecondStart--;
            //實驗狀態未完成
            if (mTimeDetect.getState() == 2){
                changeTextView(10000);
            }
            //實驗狀態開始
            else if (mTimeDetect.getState() == 1) {
                //08_21 修正過 每秒更新數值
                changeTextView(mTimeDetect.getAttention());
                //低於0進入實驗開始收集資料
                if (mSecondStart < 0) {
                    Log.d("Seconds", "開始秒數通過 偵測中");
                    //開始實驗後第一次回饋 時間間隔預設為3 (會遞減)   mSecondNeed (固定間隔時間3)
                    if (mSecondGap == mSecondNeed){
                        //呼叫gapSecond (mSecondGap開始遞減)
                        handler.postDelayed(gapSecond,500);
                        //原先設定更改數值在 開始時間 和 間隔時間後
//                        changeTextView(mTimeDetect.getAttention());

                        //取腦波資料專注跟放鬆值
                        mAttention = mTimeDetect.getAttention();
                        mAttentionList.add(mTimeDetect.getAttention());
                        mRelaxationList.add(mTimeDetect.getMeditation());

                        //情況1 數值維持在範圍內直接回饋  假設 20-80

                        //情況2 數值在水平內並且維持幾秒  假設 80以下 維持5秒
                        if (mAttention > Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getAttentionHigh())
                            && mAttention < Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getAttentionLow())) {
//                            pointDataSql=pointDataSql+endTime+","+mIdPoint[Integer.valueOf(mId)]+","+mNumPoint[Integer.valueOf(mNum)]+","+mAttention+",";

                            //收集回饋總資料
                            mFeedBackSecondOutput = mFeedBackSecondOutput + detectTotalCount +";"+endTime+ ";"+mAttention+";";
                            Log.d("多少",""+mFeedBackSecondOutput);

                            //回饋三種方式 視覺 震動 聲音
                            if (fb_Way == 0) {
                                changeSightColor(mTimeDetect.getAttention());
                            } else if (fb_Way == 1) {
                                setVibrate(1000);
                            } else if (fb_Way == 2) {
                                if (mp3Uri != null) {
                                    playBeep();
                                }

                            }
                            //回饋次數累計
                            detectTotalCount++;
                            Log.d("detectTTT", "run: Count" + detectTotalCount);
                        }
                        else if (mAttention < Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getAttentionLow())){
                            Log.d("thisthis",""+mSecondHold);
                            mSecondHold--;
                            if (mSecondHold.equals(0)){
                                mSecondHold=mSecondHoldNeed;
                                Log.d("thisthis偵測",""+mSecondHold);
                                //收集回饋總資料
                                mFeedBackSecondOutput = mFeedBackSecondOutput + detectTotalCount +";"+endTime+ ";"+mAttention+";";

                                //回饋三種方式 視覺 震動 聲音
                                if (fb_Way == 0) {
                                    changeSightColor(mTimeDetect.getAttention());
                                } else if (fb_Way == 1) {
                                    setVibrate(1000);
                                } else if (fb_Way == 2) {
                                    if (mp3Uri != null) {
                                        playBeep();
                                    }
                                }
                                //回饋次數累計
                                detectTotalCount++;
                            }
                        }

//                    if (mTimeDetect.getData().length() == 0) {
//                        Log.d("%%%", "end");
//                    } else {
//                        attention_ArrayList.add(mTimeDetect.getData());
//                    }
                    }

                }
                handler.postDelayed(this, 1000);
            }
            Log.d("%%%need",""+attention_ArrayList); //專門取attention
            Log.d("%%%data",""+mTimeDetect.getData());//取全部數值
        }
    };

    private Runnable gapSecond = new Runnable() {
        @Override
        public void run() {

            mSecondGap--;
            Log.d("second",mSecondNeed+"");
            //如果間隔不為0要繼續呼叫此執行緒倒數  否則就重新設定初始數值 並且不繼續呼叫
            if (mSecondGap !=0){
                handler.postDelayed(this,1000);
            }else if(mSecondGap ==0){
                mSecondGap =mSecondNeed;
            }

        }
    };

    /*
    public void CallApi() {
        final SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper(getContext());
        String UserId = sharedPreferencesHelper.getUserId();

        Object Ts = mTimeDetect.getThisMap().get(MindKey.Signal, 0);
        Object Attention = mTimeDetect.getThisMap().get(MindKey.Attention, 0);
        Object Meditation = mTimeDetect.getThisMap().get(MindKey.Meditation, 0);
        Object Delta = mTimeDetect.getThisMap().get(MindKey.delta, 0);
        Object Theta = mTimeDetect.getThisMap().get(MindKey.theta, 0);
        Object LowAlpha = mTimeDetect.getThisMap().get(MindKey.lowAlpha, 0);
        Object HighAlpha = mTimeDetect.getThisMap().get(MindKey.highAlpha, 0);
        Object LowBeta = mTimeDetect.getThisMap().get(MindKey.lowBeta, 0);
        Object HighBeta = mTimeDetect.getThisMap().get(MindKey.highBeta, 0);
        Object LowGamma = mTimeDetect.getThisMap().get(MindKey.lowGamma, 0);
        Object MidGamma = mTimeDetect.getThisMap().get(MindKey.midGamma, 0);

        int IntTs = 0;
        int IntAttention = 0;
        int IntMeditation = 0;
        int IntDelta = 0;
        int IntTheta = 0;
        int IntLowAlpha = 0;
        int IntHighAlpha = 0;
        int IntLowBeta = 0;
        int IntHighBeta = 0;
        int IntLowGamma = 0;
        int IntMidGamma = 0;

        IntTs = Integer.parseInt(String.valueOf(Ts));
        IntAttention = Integer.parseInt(String.valueOf(Attention));
        IntMeditation = Integer.parseInt(String.valueOf(Meditation));
        IntDelta = Integer.parseInt(String.valueOf(Delta));
        IntTheta = Integer.parseInt(String.valueOf(Theta));
        IntLowAlpha = Integer.parseInt(String.valueOf(LowAlpha));
        IntHighAlpha = Integer.parseInt(String.valueOf(HighAlpha));
        IntLowBeta = Integer.parseInt(String.valueOf(LowBeta));
        IntHighBeta = Integer.parseInt(String.valueOf(HighBeta));
        IntLowGamma = Integer.parseInt(String.valueOf(LowGamma));
        IntMidGamma = Integer.parseInt(String.valueOf(MidGamma));


        com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.Data data = new com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.Data();
        data.setUser(UserId);
        ArrayList<Esense> arrayListEsense = new ArrayList<>();
        Esense esense = new Esense();
        arrayListEsense.add(esense);
        esense.setTs(IntTs);
        esense.setAttention(IntAttention);
        esense.setMeditation(IntMeditation);
        esense.setDelta(IntDelta);
        esense.setTheta(IntTheta);
        esense.setLowAlpha(IntLowAlpha);
        esense.setHighAlpha(IntHighAlpha);
        esense.setLowBeta(IntLowBeta);
        esense.setHighBeta(IntHighBeta);
        esense.setLowGamma(IntLowGamma);
        esense.setMidGamma(IntMidGamma);
        data.setEsense(arrayListEsense);

        RetrofitClient retrofitClient = new RetrofitClient();
        retrofitClient.getService(getContext()).DataPost(data).enqueue(new Callback<OutputPost>() {
            @Override
            public void onResponse(Call<OutputPost> call, Response<OutputPost> response) {
                if (response.isSuccessful()) {

                    int UserID = response.body().getUser();
                    String metaId = response.body().getId();
                    sharedPreferencesHelper.setMetaId(metaId);


//                    SharedPreferencesHelper sharedPreferencesHelper1 = new SharedPreferencesHelper(getContext());
//                    sharedPreferencesHelper1.setMetaId(MetaId);

                    Log.e("idididid", "onResponse: " + response.body().getUser());

                }
            }

            @Override
            public void onFailure(Call<OutputPost> call, Throwable t) {
                Toast.makeText(getContext(), "網路異常請確認", Toast.LENGTH_SHORT).show();
            }
        });

        retrofitClient.getService(getContext()).ConfirmMetaData(UserId, "MBTI_EVALUATION", "true").enqueue(new Callback<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.ConfirmData.Data>() {
            @Override
            public void onResponse(Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.ConfirmData.Data> call, Response<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.ConfirmData.Data> response) {
                if (response.isSuccessful()) {

//                    int id = response.body().getResults().get(0).getId();
//                    Log.e("ididid", "onResponse: "+id );
                    int count = response.body().getCount();
                    Log.e("eeeeeee", "onResponse: " + count);

//                    sharedPreferencesHelper.setCount(count);
//                    if (count == 0) {
//                        ServiceDialogFragment serviceDialogFragment = new ServiceDialogFragment();
//                        serviceDialogFragment.show(getActivity().getFragmentManager(), "ddd");
//                        mStartButton.setEnabled(false);

//                    } else {

                    resultId = response.body().getResults().get(0).getId();
                    String metaId = sharedPreferencesHelper.getMetaId();
                    OutPutData outPutData = new OutPutData();
                    outPutData.setMeta(metaId);


//        int resultId = sharedPreferencesHelper.getResultId();
                    RetrofitClient retrofitClient2 = new RetrofitClient();
                    retrofitClient2.getService(getContext()).bindData(outPutData, resultId).enqueue(new Callback<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.Data>() {
                        @Override
                        public void onResponse(Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.Data> call, Response<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.Data> response) {
                            if (response.isSuccessful()) {
                                _result();
                                mTimeDetect.reset();
                                _state("stop");
                                mChartAdapterAtt.removeAll();
                                mChartAdapterMed.removeAll();
                            }
                        }

                        @Override
                        public void onFailure(Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.Data> call, Throwable t) {
                            Toast.makeText(getContext(), "網路異常請確認", Toast.LENGTH_SHORT).show();

                        }
                    });

//                    sharedPreferencesHelper.setResultId(resultId);

//                    }
                    Log.e("oooooooooooooooo", "onResponse: " + response.body().getCount());

                }
            }

            @Override
            public void onFailure(Call<com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.ConfirmData.Data> call, Throwable t) {
                Toast.makeText(getContext(), "網路異常請確認", Toast.LENGTH_SHORT).show();

            }
        });
//        String MetaId = sharedPreferencesHelper.getMetaId();


    }
*/
    protected void showDialog(){

        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_checkpoint, null);
        dialog.setContentView(view);
        Spinner spinnerId = view.findViewById(R.id.pointId);
        Spinner spinnerNum = view.findViewById(R.id.pointNum);
        Button buttonY = view.findViewById(R.id.buttonY);
        Button buttonN = view.findViewById(R.id.buttonN);

        //Spinner 註冊adapter
        ArrayAdapter<CharSequence> idAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.aE, android.R.layout.simple_spinner_item);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerId.setAdapter(idAdapter);

        ArrayAdapter<String> numAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,mNumPoint);
        numAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNum.setAdapter(numAdapter);

        spinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer  settingId = parent.getPositionForView(view);
                mId = settingId.toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer  settingNum = parent.getPositionForView(view);
                mNum = settingNum.toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sqlId=mIdPoint[Integer.valueOf(mId)]+mNumPoint[Integer.valueOf(mNum)];
                pointData.add(mTimeDetect.getAttention().toString());
                Integer mAttention = mTimeDetect.getAttention();
                Integer mRel = mTimeDetect.getMeditation();

                Random random = new Random();
                int answer = random.nextInt((6 - 0 + 1) + 0);
//                pointDataSql=pointDataSql+endTime+","+mIdPoint[Integer.valueOf(mId)]+","+mNumPoint[Integer.valueOf(mNum)]+","+mAttention+","+mRel+""+",";
                pointDataSql=pointDataSql+endTime+";"+mIdPoint[Integer.valueOf(mId)]+";"+mNumPoint[Integer.valueOf(mNum)]+";"+mAttention+";";

                Log.d("aaaaa",""+pointDataSql);
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

    protected void showRestartDialog(){

        Dialog dialog = new Dialog(getActivity());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_restart, null);
        dialog.setContentView(view);
        Button buttonY = view.findViewById(R.id.buttonY);
        Button buttonN = view.findViewById(R.id.buttonN);

        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Integer timer = mTimeDetect.getTimer();
//                sqlId=mIdPoint[Integer.valueOf(mId)]+mNumPoint[Integer.valueOf(mNum)];
//                pointData.add(mTimeDetect.getAttention().toString());
//                Integer mAttention = mTimeDetect.getAttention();
//                Integer mRel = mTimeDetect.getMeditation();
//
//                Random random = new Random();
//                int answer = random.nextInt((6 - 0 + 1) + 0);
//                pointDataSql=pointDataSql+timer+","+mAttention+","+mRel+","+answer+","+""+",";
                mTimeDetect.reset();
                int t = Integer.valueOf(timeId);
                    mTimeDetect.setTimer(t);

                    mTimeDetect.setRound(1);
                    mTimeDetect_data = "";
                    mTimeDetect.start();
                    _state("start");
                    fb_Way = Integer.valueOf(getActivity().getSharedPreferences("selectAttentionWay", MODE_PRIVATE)
                            .getString("USER", ""));
                    //取得開始秒數
                    mSecondStart =Integer.valueOf(feedbackDataList.get(Integer.valueOf(settingId)).getWaySecond());

                    handler.postDelayed(getData,500);
                Log.d("aaaaa",""+pointDataSql);
                dialog.cancel();
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

    public void LoadData() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM settingDataList", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_Name));
            String item = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_Item));
            String attentionHigh = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_AttentionHigh));
            String attentionLow = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_AttentionLow));
            String attentionFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionFeedBackWay));
            String attentionMp3Uri = cursor.getString(cursor.getColumnIndex(COLUMN_AttentionMp3Uri));
            String relaxationHigh = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_RelaxationHigh));
            String relaxationLow = cursor.getString(cursor.getColumnIndex(SettingDBContract.SettingDataEntry.COLUMN_RelaxationLow));
            String relaxationFeedBackWay = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationFeedBackWay));
            String relaxationMp3Uri = cursor.getString(cursor.getColumnIndex(COLUMN_RelaxationMp3Uri));
            String feedBackWaySecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWaySecond)); //忽略
            String feedBackWayHoldSecond = cursor.getString(cursor.getColumnIndex(COLUMN_HoldSecond));
            String feedBackWayStopTipSecond = cursor.getString(cursor.getColumnIndex(COLUMN_FeedBackWayStopTipSecond)); //間隔
            FeedbackData feedbackData = new FeedbackData(id, name, item, attentionHigh, attentionLow, attentionFeedBackWay,attentionMp3Uri,
                    relaxationHigh, relaxationLow, relaxationFeedBackWay,relaxationMp3Uri,feedBackWaySecond, feedBackWayHoldSecond, feedBackWayStopTipSecond);
            feedbackDataList.add(feedbackData);
            Log.d("刷新",""+feedBackWaySecond + feedBackWayStopTipSecond);
        }
        cursor.close();
    }

    //振動方式
    public void setVibrate(int time){
        Vibrator vibrator =(Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }
    public void playBeep() {
        try {
//            if (mediaPlayer.isPlaying()) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//
//            }
            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor descriptor = getActivity().getAssets().openFd("voice.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
//            m.setVolume(1f, 1f);
//            m.setLooping(true);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
