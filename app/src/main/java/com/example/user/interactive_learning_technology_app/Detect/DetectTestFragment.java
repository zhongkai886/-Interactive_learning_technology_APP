package com.example.user.interactive_learning_technology_app.Detect;

import android.app.Service;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.interactive_learning_technology_app.R;

import java.util.Timer;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DetectTestFragment extends Fragment {
    private Button testButton;
    private Button againButton;
    private TextView textView;
    private Delayed delayed;
    private TimeUnit timeUnit ;
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
        textView = (TextView) view.findViewById(R.id.test);
        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "可以吧", Toast.LENGTH_SHORT).show();
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVibrate(1000);
//                delayed.wait();
//                demo demo =new demo();
//                demo.start();
            }
        });
        return view;
    }
    public void setVibrate(int time){
        Vibrator vibrator =(Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }
    public void setColor(){
        testButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    public  class  demo extends Thread{
        public void run(){
//            try {
//
//                textView.setBackgroundResource(R.drawable.color_72dpi);
//                Thread.sleep(2000);
//
//                textView.setBackgroundResource(R.drawable.logo_icon);

//            }catch (InterruptedException e) {
//                System.out.println("Thread ERROR");
//            }
        }
    }

}