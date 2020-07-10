package com.alchemy.mindlibrary.tool;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alchemy.mindlibrary.MindKey;

import wjk.java.array.ArrayMap;

public class MindDetectTool {

    public static final int STATE_IDLE = 0;
    public static final int STATE_RUN = 1;
    public static final int STATE_STOP = 2;
    public static final int STATE_PAUSE = 3;

    public interface Listen{
    }

    public interface ListenMindPower extends Listen{
        void onMindPower();
    }

    public interface ListenTimerUp extends Listen{
        void onTimerUp();
    }

    public interface ListenFilter extends Listen{
        void onFilter(boolean f);
    }

    public interface ListenRawData extends Listen{
        void onRawData(int data);
    }

    boolean isInstance(Class cls, Object o){
        return (o!=null) ? cls.isInstance(o) : false;
    }

    protected Listen mListen;
    protected int state = STATE_IDLE;
    protected ArrayMap mArrayMap = new ArrayMap();;
    protected boolean storeAll = false;

    public int signal;
    public int attention;
    public int meditation;
    public int lowAlpha;
    public int lowBeta;
    public int lowGamma;
    public int highAlpha;
    public int highBeta;
    public int midGamma;
    public int theta;
    public int delta;

    public MindDetectTool(){}

    //TODO======]STATE[======

    public void start(){
        if(state == STATE_STOP)return;
        state = STATE_RUN;
    }

    public void pause(){
        if(state == STATE_STOP)return;
        state = STATE_PAUSE;
    }

    public void paustart(){
        if(state == STATE_STOP)return;
        state = state == STATE_PAUSE? STATE_RUN:STATE_PAUSE;
    }

    public void stop(){
        state = STATE_STOP;
    }

    public void reset(){
        state = STATE_IDLE;
        clear();
    }

    public int getState(){
        return state;
    }

    //TODO======]Method[======

    public void setListen(Listen l){
        mListen = l;
    }

    public void clear(){
        clearData();
        attention = 0;
        meditation = 0;
        lowAlpha = 0;
        lowBeta = 0;
        lowGamma = 0;
        highAlpha = 0;
        highBeta = 0;
        midGamma = 0;
        theta = 0;
        delta = 0;

        //timer
        count_timer = 0;
    }

    public void clearData(){
        mArrayMap = new ArrayMap();
    }

    public String getData(){
        return mArrayMap.toString();
    }
    public Integer getAttention(){
        return attention;
    }
    public Integer getMeditation(){
        return meditation;
    }



    public void setStoreAll(boolean e){
        storeAll = e;
    }

    //TODO======]MIND[======

    protected void onMindStore(){
        mArrayMap.store();
    }

    protected boolean onMindPower(){
        if(storeAll || (signal == 0 && (attention != 0 || meditation != 0))){
            Log.d("會出現嗎",""+attention);

            if(onFilter())onMindStore();

            if(timer>0) {
                count_timer++;
                if (isTimeUp()) onTimeUp();
            }
            return true;
        }
        return false;
    }

    protected boolean onFilter(){
        boolean f = isFilter();
        if(isInstance(ListenFilter.class, mListen))((ListenFilter)mListen).onFilter(f);
        return !f;
    }

    protected void onTimeUp(){
        stop();
        if(isInstance(ListenTimerUp.class, mListen))((ListenTimerUp)mListen).onTimerUp();
    }

    protected boolean onMind(Message msg){
        if(state != STATE_RUN)return true;

        switch(msg.what){
            case MindsetValue.MSG_POOR_SIGNAL:
                signal = msg.arg1;
                mArrayMap.put(MindKey.Signal, msg.arg1);
                break;
            case MindsetValue.MSG_ATTENTION:
                attention = msg.arg1;
                mArrayMap.put(MindKey.Attention, msg.arg1);
                break;
            case MindsetValue.MSG_MEDITATION:
                meditation = msg.arg1;
                mArrayMap.put(MindKey.Meditation, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_LOWALPHA:
                lowAlpha = msg.arg1;
                mArrayMap.put(MindKey.lowAlpha, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_LOWBETA:
                lowBeta = msg.arg1;
                mArrayMap.put(MindKey.lowBeta, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_LOWGAMMA:
                lowGamma = msg.arg1;
                mArrayMap.put(MindKey.lowGamma, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_HIGHALPHA:
                highAlpha = msg.arg1;
                mArrayMap.put(MindKey.highAlpha, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_HIGHBETA:
                highBeta = msg.arg1;
                mArrayMap.put(MindKey.highBeta, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_MIDGAMMA:
                midGamma = msg.arg1;
                mArrayMap.put(MindKey.midGamma, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_DELTA:
                delta = msg.arg1;
                mArrayMap.put(MindKey.delta, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_THETA:
                theta = msg.arg1;
                mArrayMap.put(MindKey.theta, msg.arg1);
                break;
            case MindsetValue.MSG_EEG_POWER:
                onMindPower();
                if(isInstance(ListenMindPower.class, mListen))((ListenMindPower)mListen).onMindPower();
                break;
            case MindsetValue.MSG_RAW_DATA:
                if(isInstance(ListenRawData.class, mListen))((ListenRawData)mListen).onRawData(msg.arg1);
                break;
        }
        return false;
    }

    public Handler getHandler(){
        return mHandler;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return onMind(msg);
        }
    });


    //TODO========]Timer[========
    protected int timer;
    protected int count_timer;

    public MindDetectTool(int t){
        setTimer(t);
        filtenable = false;
    }

    //Timer
    public void setTimer(int t){
        timer = t;
    }

    public int getTimer(){
        return timer;
    }

    public int getCountTime(){
        return count_timer;
    }

    public int getCountdownTime(){
        return timer - count_timer;
    }

    public boolean isTimeUp(){
        return count_timer >= timer;
    }


    //TODO========]Filter[========
    public int filt_alphabeta;
    public int filt_theta;
    public boolean filtenable = false;

    public MindDetectTool(int t, int alphabeta, int theta) {
        setTimer(t);
        filtenable = true;
        filt_alphabeta = alphabeta;
        filt_theta = theta;
    }

    public void setFilter(int alphabeta, int theta){
        filt_alphabeta = alphabeta;
        filt_theta = theta;
    }

    public void setFiltEnable(boolean enable){
        filtenable = enable;
    }

    public boolean isFilter(){
        int ab = highAlpha + lowAlpha + highBeta + lowBeta;
        int t = this.theta;
        return (ab > filt_alphabeta && t > filt_theta) & filtenable;
    }

}
