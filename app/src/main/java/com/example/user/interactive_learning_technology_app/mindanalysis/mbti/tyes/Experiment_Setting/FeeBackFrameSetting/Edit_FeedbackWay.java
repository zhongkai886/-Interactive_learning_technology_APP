package com.example.user.interactive_learning_technology_app.mindanalysis.mbti.tyes.Experiment_Setting.FeeBackFrameSetting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


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
    private String mWayHoldSecond;

    private String mWayStopTipSecond;

    public Edit_FeedbackWay(String Id,String Name,String Item ,String AttentionHigh ,String AttentionLow,
                            String AttentionWay, String RelaxationHigh,String RelaxationLow,
                            String RelaxationWay,String WaySecond,String WayHoldSecond,String WayStopTipSecond) {
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
        this.mWayHoldSecond=WayHoldSecond;
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
        final EditText EdtFbwHoldSec =(EditText) view.findViewById(R.id.fbwHoldSec);
        final EditText EdtFbwSecTips =(EditText) view.findViewById(R.id.fbwSecTips);
        RadioButton attSight = (RadioButton) view.findViewById(R.id.attSight);
        RadioButton attShock = (RadioButton) view.findViewById(R.id.attShock);
        RadioButton attVoice = (RadioButton) view.findViewById(R.id.attVoice);
        RadioButton relSight = (RadioButton) view.findViewById(R.id.relSight);
        RadioButton relShock = (RadioButton) view.findViewById(R.id.relShock);
        RadioButton relVoice = (RadioButton) view.findViewById(R.id.relVoice);
        EdtName.setText(mName);
        EdtItem.setText(mItem);

        if (mAttentionWay==null){
        } else{
            if (Integer.valueOf(mAttentionWay)==0){
                attSight.setChecked(true);
            }
            if (Integer.valueOf(mAttentionWay)==1){
                attShock.setChecked(true);
            }
            if (Integer.valueOf(mAttentionWay)==2){
                attVoice.setChecked(true);
            }

        }
        if (mRelaxationWay==null){
        } else{
            if (Integer.valueOf(mRelaxationWay)==0){
                relSight.setChecked(true);
            }
            if (Integer.valueOf(mRelaxationWay)==1){
                relShock.setChecked(true);
            }
            if (Integer.valueOf(mRelaxationWay)==2){
                relVoice.setChecked(true);
            }

        }



        EdtAttentionHigh.setText(mAttentionHigh);
        EdtAttentionLow.setText(mAttentionLow);
        EdtRelaxationHigh.setText(mRelaxationHigh);
        EdtRelaxationLow.setText(mRelaxationLow);
        EdtFbwSec.setText(mWaySecond);
        EdtFbwHoldSec.setText(mWayHoldSecond);
        EdtFbwSecTips.setText(mWayStopTipSecond);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FeedbackFrameSettingsFragment SettingFragment =new FeedbackFrameSettingsFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.center, SettingFragment);
                fragmentTransaction.commit();
            }
        });

        return view ;
    }
}
