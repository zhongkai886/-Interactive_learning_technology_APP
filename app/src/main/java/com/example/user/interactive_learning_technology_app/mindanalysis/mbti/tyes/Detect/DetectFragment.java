package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alchemy.mindcontroller.MindController;
import com.alchemy.mindcontroller.MindControllerFactory;
import com.alchemy.mindlibrary.MindKey;
import com.alchemy.mindlibrary.tool.MindDetectTool;
import com.alchemy.mindlibrary.tool.MindDetectToolMulti;
import com.example.user.interactive_learning_technology_app.R;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.BindMetaData.OutPutData;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.Esense;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.PostData.OutputPost;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.API.RetrofitClient;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBHelper;
import com.example.user.interactive_learning_technology_app.widget.PreferencesCenter;
import com.example.user.interactive_learning_technology_app.widget.StringMultiple;
import com.example.user.interactive_learning_technology_app.wjk.database.SQLiteCenter;
import com.example.user.interactive_learning_technology_app.wjk.database.UserTable;
import com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SharedPreferencesHelper.SharedPreferencesHelper;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import wjk.android.chart.adapter.NumberAdapter;

import static android.content.Context.MODE_PRIVATE;
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
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_Number;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_PointInTime;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationHigh;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationLow;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMax;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.COLUMN_RelaxationMin;
import static com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.SearchDatabase.SearchDBContract.SearchDataEntry.TABLE_NAME;


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
    public EditText mUserNameEdit;
    public TextView mTimerText;
    public TextView mPointValue;
    public MindDetectTool mindDetectTool ;

    Handler handler = new Handler();
    boolean toast = true;

    //member
    public NumberAdapter mChartAdapterAtt;
    public NumberAdapter mChartAdapterMed;
    private String mTimeDetect_data;
    public String mindData;
    public String nameData;
    public String rawData;
    private ArrayList<String> attention_ArrayList;

    @Override
    public void onTimerUp() {
//        CallApi();
        _result();
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
                    mTimerText.setText(mTimeDetect.getCountdownRound() + "-" + mTimeDetect.getCountdownTime());
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
        mStartButton = (Button) view.findViewById(R.id.detectButton);
        mPauseButton = (Button) view.findViewById(R.id.removeButton);
        mStopButton = (Button) view.findViewById(R.id.stopButton);
        mUserNameEdit = (EditText) view.findViewById(R.id.fragment_detect_username);
        mPointValue = (TextView)view.findViewById(R.id.recordPointTx);
        _initView();
        return view;
    }

    public void _initView() {
        //share選擇的時間
        String timeId = getActivity().getSharedPreferences("timeSelect", MODE_PRIVATE)
                .getString("USER", "");

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t = 5;
//                        Integer.valueOf(timeId);
                mTimeDetect.setTimer(t);

                mTimeDetect.setRound(1);
                mTimeDetect_data = "";
                mTimeDetect.start();
                _state("start");

                handler.postDelayed(updateDevice,500);


            }
        });
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeDetect.paustart();
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
    }

    private void _state(String state) {
        if (state.equals("start")) {
            mStartButton.setVisibility(View.GONE);
            mStopButton.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.VISIBLE);
        } else if (state.equals("pause")) {
            Log.d("??","結束按鈕");
        } else if (state.equals("stop")) {
            mStartButton.setVisibility(View.VISIBLE);
            mStopButton.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.GONE);
//            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            final ResultFragment resultFragment = new ResultFragment();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.center, resultFragment);
//            fragmentTransaction.commit();
        }
    }

    private void _result(){ //測驗資料的19筆欄位
        final String SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Name + " VARCHAR(50), " +
                COLUMN_Number + " VARCHAR(50), " +
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
        sqLiteDatabase.execSQL(SQL);

        String sql = "INSERT into '" + TABLE_NAME + "' ( '" +COLUMN_Number
                + "','" + COLUMN_Name
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
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Object[] mValue = new Object[]{"編號","名稱","日期","Attention",
                "總回饋次數","a高","a低","r高",
                "r低","a最大","a最小","r最大",
                "r最小","間隔秒數", "忽略","平均a",
                "平均r","data"};



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

    }
    public void changeTextView(Integer attentionValue){
        mPointValue.setText(attentionValue.toString());
    }

    private Runnable updateDevice = new Runnable() {
        public void run() {
            if (mTimeDetect.getState() == 2){
                changeTextView(10000);
            }
            else if (mTimeDetect.getState() == 1){
                Log.d("%%%",""+mTimeDetect.getAttention());
//                Random random = new Random();
//                int answer = random.nextInt((100 - 0 + 1) + 0);
                changeTextView(mTimeDetect.getAttention());
                if (mTimeDetect.getData().length()==0){
                    Log.d("%%%","end");
                }else{
                    attention_ArrayList.add(mTimeDetect.getData());
                }
//                attention_ArrayList.add(mTimeDetect.getAttention().toString());
                handler.postDelayed(this, 1000);
            }
            Log.d("%%%need",""+attention_ArrayList); //專門取attention
            Log.d("%%%data",""+mTimeDetect.getData());//取全部數值
        }
    };
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
}
