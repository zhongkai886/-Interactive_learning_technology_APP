package com.example.user.interactive_learning_technology_app.Experiment_Setting.FeeBackFrameSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.interactive_learning_technology_app.Main.MainFragment;
import com.example.user.interactive_learning_technology_app.R;

public class Edit_FeedbackWay extends Fragment {
    private String mId;
    private String mName;
    private String mItem;
    private String mAttentionHigh;
    private String mAttentionLow;
    private String mAttentionWay;
    private String mRelaxationHigh;
    private String mRelaxationLow;
    private String mRelaxationWay;
    private String mWaySecond;
    private String mWayStopTipSecond;

    public Edit_FeedbackWay(String Id,String Name,String Item ,String AttentionHigh ,String AttentionLow,
                            String AttentionWay, String RelaxationHigh,String RelaxationLow,
                            String RelaxationWay,String WaySecond,String WayStopTipSecond) {
        this.mId = Id;
        this.mName=Name;
        this.mItem=Item;
        this.mAttentionHigh=AttentionHigh;
        this.mAttentionLow=AttentionLow;
        this.mAttentionWay=AttentionWay;
        this.mRelaxationHigh=RelaxationHigh;
        this.mRelaxationLow=RelaxationLow;
        this.mRelaxationWay=RelaxationWay;
        this.mWaySecond=WaySecond;
        this.mWayStopTipSecond=WayStopTipSecond;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_feedbackway, container, false);
        Button BackButton = (Button) view.findViewById(R.id.BackSettingButton);
        final EditText EdtName = (EditText) view.findViewById(R.id.name);
        final EditText EdtItem =(EditText) view.findViewById(R.id.item);
        final EditText EdtAttentionHigh =(EditText) view.findViewById(R.id.attentionHigh);
        final EditText EdtAttentionLow =(EditText) view.findViewById(R.id.attentionLow);
        final EditText EdtRelaxationHigh =(EditText) view.findViewById(R.id.relaxationHigh);
        final EditText EdtRelaxationLow =(EditText) view.findViewById(R.id.relaxationLow);
        final EditText EdtFbwSec =(EditText) view.findViewById(R.id.fbwSec);
        final EditText EdtFbwSecTips =(EditText) view.findViewById(R.id.fbwSecTips);
        EdtName.setText(mName);
        EdtItem.setText(mItem);
        EdtAttentionHigh.setText(mAttentionHigh);
        EdtAttentionLow.setText(mAttentionLow);
        EdtRelaxationHigh.setText(mAttentionWay);
        EdtRelaxationLow.setText(mRelaxationHigh);
        EdtFbwSec.setText(mRelaxationLow);
        EdtFbwSecTips.setText(mWayStopTipSecond);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FeedbackFrameSettingsActivity SettingFragment =new FeedbackFrameSettingsActivity();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, SettingFragment);
                fragmentTransaction.commit();
            }
        });

        return view ;
    }
}
