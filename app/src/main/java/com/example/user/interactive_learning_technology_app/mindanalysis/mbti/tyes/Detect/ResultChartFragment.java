package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Detect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.interactive_learning_technology_app.R;

public class ResultChartFragment extends Fragment {

    public static Fragment newInstance(int[] attention, int[] meditation,
                                       double theta, double highAlpha, double lowAlpha, double highBeta,
                                       double lowBeta, double midGamma, double lowGamma) {
        return newInstance(toPer(attention), toPer(meditation),
                toInt(theta),
                toInt(highAlpha),
                toInt(lowAlpha),
                toInt(highBeta),
                toInt(lowBeta),
                toInt(midGamma),
                toInt(lowGamma));
    }

    public static ResultChartFragment newInstance(String param1, String param2) {
        ResultChartFragment fragment = new ResultChartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultchart, container, false);

        return view;
    }
    private static int[] toPer(int[] arr){
        int s = 0;
        for(int i: arr)s += i;
        for(int i = 0; i<arr.length; i++){
            if(s == 0)arr[i] = 0;
            else{
                arr[i] = (int) (arr[i] * 100f / s + 0.5f);
            }
        }

        // 最後一個值 ，補植到10值
        s = 0;
        for(int i: arr)s += i;
        if(s != 100)arr[arr.length-1] += 100-s;

        return arr;
    }
    private static int toInt(double d){
        return (int) (d*100+0.5);
    }
    public static Fragment newInstance(int[] attention, int[] meditation,
                                       int theta, int highAlpha, int lowAlpha, int highBeta,
                                       int lowBeta, int midGamma, int lowGamma) {
        ResultChartFragment f = new ResultChartFragment();
        f._attention = attention;
        f._meditation = meditation;
        f._theta = theta;
        f._highAlpha = highAlpha;
        f._lowAlpha = lowAlpha;
        f._highBeta = highBeta;
        f._lowBeta = lowBeta;
        f._midGamma = midGamma;
        f._lowGamma = lowGamma;
        return f;
    }
    //==========[static]==========
    //argv
    private int[] _attention;
    private int[] _meditation;

    private int _theta;
    private int _highAlpha;
    private int _lowAlpha;
    private int _highBeta;
    private int _lowBeta;
    private int _midGamma;
    private int _lowGamma;
}
