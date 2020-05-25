package com.example.user.interactive_learning_technology_app.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferencesCenter {

    private SharedPreferences mSharedPreferences;

    public boolean DetectMind_filter_enable;
    public int DetectMind_filter_alphabeta;
    public int DetectMind_filter_theta;
    public boolean ResultColor_detail_enable;
    public int MindcolorAlgo_CountTH_yellow;
    public int MindcolorAlgo_CountTH_blue;
    public int MindcolorAlgo_CountTH_green;
    public boolean MindcolorAlgo_PriorityOrder_enable;
    public int MindcolorAlgo_PriorityOrder;
    public boolean MindcolorAlgo_PriorityThan_enable;
    public String MindcolorAlgo_PriorityThan;
    public int MindcolorAlgo_YijingTheta;

    public PreferencesCenter(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        defaultValue();
        load();
    }

    public void defaultValue() {
        DetectMind_filter_enable = false;
        DetectMind_filter_alphabeta = 125000;
        DetectMind_filter_theta = 104000;
        ResultColor_detail_enable = true;
        MindcolorAlgo_PriorityOrder_enable = true;
        MindcolorAlgo_PriorityThan_enable = true;
        MindcolorAlgo_PriorityOrder = 321;
        MindcolorAlgo_PriorityThan = "0111000101010000";
        MindcolorAlgo_CountTH_yellow = 2;
        MindcolorAlgo_CountTH_blue = 2;
        MindcolorAlgo_CountTH_green = 2;
        MindcolorAlgo_YijingTheta = 86;
    }

    public void load() {
        SharedPreferences sp = mSharedPreferences;
        DetectMind_filter_enable = sp.getBoolean("DetectMind_filter_enable", DetectMind_filter_enable);
        DetectMind_filter_alphabeta = sp.getInt("DetectMind_filter_alphabeta", DetectMind_filter_alphabeta);
        DetectMind_filter_theta = sp.getInt("DetectMind_filter_theta", DetectMind_filter_theta);
        ResultColor_detail_enable = sp.getBoolean("ResultColor_detail_enable", ResultColor_detail_enable);
        MindcolorAlgo_PriorityOrder_enable = sp.getBoolean("MindcolorAlgo_PriorityOrder_enable", MindcolorAlgo_PriorityOrder_enable);
        MindcolorAlgo_PriorityThan_enable = sp.getBoolean("MindcolorAlgo_PriorityThan_enable", MindcolorAlgo_PriorityThan_enable);
        MindcolorAlgo_PriorityOrder = sp.getInt("MindcolorAlgo_PriorityOrder", MindcolorAlgo_PriorityOrder);
        MindcolorAlgo_PriorityThan = sp.getString("MindcolorAlgo_PriorityThan_String", MindcolorAlgo_PriorityThan);
        MindcolorAlgo_CountTH_yellow = sp.getInt("MindcolorAlgo_CountTH_yellow", MindcolorAlgo_CountTH_yellow);
        MindcolorAlgo_CountTH_blue = sp.getInt("MindcolorAlgo_CountTH_blue", MindcolorAlgo_CountTH_blue);
        MindcolorAlgo_CountTH_green = sp.getInt("MindcolorAlgo_CountTH_green", MindcolorAlgo_CountTH_green);
        MindcolorAlgo_YijingTheta = sp.getInt("MindcolorAlgo_YijingTheta", MindcolorAlgo_YijingTheta);
    }

    public void save() {
        Editor ed = mSharedPreferences.edit();

        ed.putBoolean("DetectMind_filter_enable", DetectMind_filter_enable);
        ed.putInt("DetectMind_filter_alphabeta", DetectMind_filter_alphabeta);
        ed.putInt("DetectMind_filter_theta", DetectMind_filter_theta);
        ed.putBoolean("ResultColor_detail_enable", ResultColor_detail_enable);
        ed.putBoolean("MindcolorAlgo_PriorityOrder_enable", MindcolorAlgo_PriorityOrder_enable);
        ed.putBoolean("MindcolorAlgo_PriorityThan_enable", MindcolorAlgo_PriorityThan_enable);
        ed.putInt("MindcolorAlgo_PriorityOrder", MindcolorAlgo_PriorityOrder);
        ed.putString("MindcolorAlgo_PriorityThan_String", MindcolorAlgo_PriorityThan);
        ed.putInt("MindcolorAlgo_CountTH_yellow", MindcolorAlgo_CountTH_yellow);
        ed.putInt("MindcolorAlgo_CountTH_blue", MindcolorAlgo_CountTH_blue);
        ed.putInt("MindcolorAlgo_CountTH_green", MindcolorAlgo_CountTH_green);
        ed.putInt("MindcolorAlgo_YijingTheta", MindcolorAlgo_YijingTheta);
        ed.commit();
    }

    public void save(Type type){
        Editor ed = mSharedPreferences.edit();

        switch(type){
            case Yijing:
                ed.putInt("MindcolorAlgo_YijingTheta", MindcolorAlgo_YijingTheta);
                break;
            case Order:
                ed.putBoolean("MindcolorAlgo_PriorityOrder_enable", MindcolorAlgo_PriorityOrder_enable);
                ed.putInt("MindcolorAlgo_PriorityOrder", MindcolorAlgo_PriorityOrder);
                break;
            case Count:
                ed.putInt("MindcolorAlgo_CountTH_yellow", MindcolorAlgo_CountTH_yellow);
                ed.putInt("MindcolorAlgo_CountTH_blue", MindcolorAlgo_CountTH_blue);
                ed.putInt("MindcolorAlgo_CountTH_green", MindcolorAlgo_CountTH_green);
                break;
            case Filt:
                ed.putBoolean("DetectMind_filter_enable", DetectMind_filter_enable);
                ed.putInt("DetectMind_filter_alphabeta", DetectMind_filter_alphabeta);
                ed.putInt("DetectMind_filter_theta", DetectMind_filter_theta);
                break;
            case Than:
                ed.putBoolean("MindcolorAlgo_PriorityThan_enable", MindcolorAlgo_PriorityThan_enable);
                ed.putString("MindcolorAlgo_PriorityThan_String", MindcolorAlgo_PriorityThan);
                break;
        }

        ed.commit();
    }

    public enum Type{
        Than, Filt, Count, Order, Yijing
    }

}
