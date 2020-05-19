package com.alchemy.mindlibrary.tool;

import java.util.ArrayList;

import wjk.java.array.ArrayMap;

public class MindDetectToolMulti extends MindDetectTool {

    public MindDetectToolMulti() {}

    public MindDetectToolMulti(int t) {
        super(t);
    }

    public MindDetectToolMulti(int t, int alphabeta, int theta) {
        super(t, alphabeta, theta);
    }

    //TODO========]MindDetectTool[========
    public interface Listen extends ListenMindPower, ListenTimerUp, ListenFilter {
        void onTimerUp();
        void onFilter(boolean f);
        void onRound(int r);
        void onMindPower();
    }


    @Override
    public void clear() {
        super.clear();
        count_timer = 0;
        count_round = 0;
        dataArray = new ArrayList<>();
    }

    @Override
    protected void onTimeUp() {
        count_round++;
        dataArray.add(getData());
        if(mListen instanceof Listen)((Listen) mListen).onRound(count_round);
        if(isRoundUp()){
            super.onTimeUp();
        }else{
            count_timer = 0;
            clearData();
        }
    }

    //TODO========]Round[========
    protected int round = 1;
    protected int count_round;
    protected ArrayList<String> dataArray = new ArrayList<>();
    public void setRound(int r){
        round = r;
    }

    public int getRound(){
        return round;
    }

    public int getCountRound(){
        return count_round;
    }

    public int getCountdownRound(){
        return round - count_round;
    }

    public boolean isRoundUp(){
        return count_round >= round;
    }
    public ArrayMap getThisMap(){
        return mArrayMap ;
    }

    public String[] getDataArray(){
        return dataArray.toArray(new String[dataArray.size()]);
    }


    public boolean isRunning(){
        return state == STATE_RUN;
    }
}
