package com.example.user.interactive_learning_technology_app.Detect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.alchemy.mindcontroller.MindController;
import com.alchemy.mindlibrary.tool.MindDetectToolMulti;
import com.example.user.interactive_learning_technology_app.R;

import wjk.android.chart.adapter.NumberAdapter;


public class DetectFragment extends Fragment {
    private boolean mInitialCreate;
    private onCompleteListener mListener;
    //mind
    private MindController mMindCenter;
    private MindDetectToolMulti mTimeDetect;
    private int resultId;
    //view
    public Button mStartButton;
    public Button mPauseButton;
    public Button mStopButton;
    public EditText mTimerEdit;
    public Spinner mTimerSpinner;

    //member
    public NumberAdapter mChartAdapterAtt;
    public NumberAdapter mChartAdapterMed;
    private String mTimeDetect_data;
    public String mindData;
    public String nameData;
    public String rawData;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detect, container, false);

        mStartButton = (Button) view.findViewById(R.id.detectButton);
        mPauseButton = (Button) view.findViewById(R.id.removeButton);
        mStopButton = (Button) view.findViewById(R.id.stopButton);
        mTimerSpinner = (Spinner) view.findViewById(R.id.timetime);
//        mTimerEdit = (EditText) view.findViewById(R.id.fragment_detect_timerEdit);
//        _initView();
        return view;
    }

    private void _initView() {
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerEdit.getVisibility() != View.GONE) {
                    int t = Integer.valueOf(mTimerEdit.getText().toString());
                    mTimeDetect.setTimer(t);
                }

                mTimeDetect.setRound(Integer.valueOf((String) mTimerSpinner.getSelectedItem()));
                mTimeDetect_data = "";
                mTimeDetect.start();

                _state("start");
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
            mStopButton.setVisibility(View.VISIBLE);
            mPauseButton.setVisibility(View.VISIBLE);
        } else if (state.equals("pause")) {

        } else if (state.equals("stop")) {
            mStartButton.setVisibility(View.VISIBLE);
            mStopButton.setVisibility(View.GONE);
            mPauseButton.setVisibility(View.GONE);
        }
    }
}
