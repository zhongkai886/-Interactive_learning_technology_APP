package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.interactive_learning_technology_app.R;

public class aaa extends Fragment {
    private String S;
    private String S2;

    public aaa(String S) {
    this.S=S;
    this.S=S;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aaa, container, false);
        TextView textView = view.findViewById(R.id.a98);
        TextView textView2 = view.findViewById(R.id.a99);
        textView.setText(S);

        return view ;
    }
}
