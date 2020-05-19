package com.alchemy.mindlibrary.helper;

import wjk.java.array.ArrayMap;

public class MindWaveHelperMulti {

    public final ArrayMap[] Array;
    public MindWaveHelper[] Helpers;
    boolean analysis = false;

    public MindWaveHelperMulti(ArrayMap... d) {
        Array = d;
    }


    public void analysisAverage() {
        analysis = true;
        Helpers = new MindWaveHelper[Array.length];

        for(int i = 0 ; i < Array.length; i++) {
            Helpers[i] = new MindWaveHelper(Array[i]);
            Helpers[i].analysisAverage();
        }
    }

    public void analysisProportion() {
        analysis = true;
        Helpers = new MindWaveHelper[Array.length];

        for(int i = 0 ; i < Array.length; i++) {
            Helpers[i] = new MindWaveHelper(Array[i]);
            Helpers[i].analysisProportion();
        }
    }

    public MindWaveHelper bestTheta(){
        MindWaveHelper helper = null;
        double theta = 0;
        for(MindWaveHelper h :Helpers){
            if(h.Theta>theta){
                helper = h;
                theta = h.Theta;
            }
        }
        if(helper == null)return new MindWaveHelper();
        return helper;
    }
}
