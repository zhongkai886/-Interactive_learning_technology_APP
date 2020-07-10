package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.app.Service;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.R;

import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class DetectTestFragment extends Fragment {
    private Button testButton;
    private Button againButton;
    private Button detectButton;
    private TextView detectIdTextView;
    private Delayed delayed;
    private TimeUnit timeUnit ;
    Handler handler = new Handler();
    boolean onClickBoolean = false;
    public DetectTestFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detect_test, container, false);
        testButton = (Button) view.findViewById(R.id.testButton);
        againButton = (Button) view.findViewById(R.id.againTestButton);
        detectIdTextView = (TextView) view.findViewById(R.id.detectId);
        detectButton = (Button) view.findViewById(R.id.detectButton);

        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final DetectFragment detectFragment = new DetectFragment();

        String loginId = getActivity().getSharedPreferences("detectId", MODE_PRIVATE)
                .getString("USER", "");
        String fb_way = getActivity().getSharedPreferences("selectId", MODE_PRIVATE)
                .getString("USER", "");
        String fb_Way = getActivity().getSharedPreferences("selectAttentionWay", MODE_PRIVATE)
                .getString("USER", "");
        detectIdTextView.setText(loginId);
        Log.d("wayway", "onCreateView: "+fb_Way);
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fb_Way.equals("0")){
                    if (onClickBoolean==false){
                        onClickBoolean=true;
                        handler.postDelayed(updateDevice,500);
                    }else{
                        handler.removeCallbacks(updateDevice);
                        onClickBoolean=false;
                        testButton.setBackgroundColor(testButton.getContext().getResources().getColor(R.color.white));
                    }
                }
                else if (fb_Way.equals("1")){
                    setVibrate(1000);
                }else{

                }
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fb_Way.equals("0")){
                    if (onClickBoolean==false){
                        onClickBoolean=true;
                        handler.postDelayed(updateDevice,500);
                    }else{
                        handler.removeCallbacks(updateDevice);
                        onClickBoolean=false;
                        testButton.setBackgroundColor(testButton.getContext().getResources().getColor(R.color.white));
                    }
                }
                else if (fb_Way.equals("1")){
                    setVibrate(1000);

                }else{

                }

            }
        });
        detectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, detectFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
    public void setVibrate(int time){
        Vibrator vibrator =(Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }

    Boolean na = true;
    private Runnable updateDevice = new Runnable() {
        public void run() {
            if (na==true){
                testButton.setBackgroundColor(testButton.getContext().getResources().getColor(R.color.colorPrimaryDark));
                na=false;
            }else{
                testButton.setBackgroundColor(testButton.getContext().getResources().getColor(R.color.white));
                na=true;
            }
            handler.postDelayed(this, 1000);
        }
    };

}